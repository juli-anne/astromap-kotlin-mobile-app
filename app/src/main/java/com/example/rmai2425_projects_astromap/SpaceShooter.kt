package com.example.rmai2425_projects_astromap

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.HighScore
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlin.collections.ArrayList

class SpaceShooter(context: Context) : View(context) {
    private val background = BitmapFactory.decodeResource(resources, R.drawable.background)
    private val lifeImage: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.life), 64, 64, true
    )
    private var handler: Handler? = null
    private val UPDATE_MILLIS: Long = 30
    private val scorePaint = Paint().apply {
        color = Color.RED
        textSize = 80f
        textAlign = Paint.Align.LEFT
    }
    private var points = 0
    private var life = 3
    private var paused = false
    private var comet: Comet
    private var enemyComet: EnemyComet
    private val cometShots = ArrayList<Shot>()
    private val explosions = ArrayList<Explosion>()

    companion object {
        var screenWidth = 0
        var screenHeight = 0
    }

    init {
        val display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        comet = Comet(context)
        enemyComet = EnemyComet(context)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(background, 0f, 0f, null)
        canvas.drawText("Pt: $points", 0f, 80f, scorePaint)

        for (i in 0 until life) {
            val x = screenWidth - (i + 1) * lifeImage.width - 10 * i
            val y = 16
            canvas.drawBitmap(lifeImage, x.toFloat(), y.toFloat(), null)
        }

        if (life <= 0) {
            paused = true
            handler = null
            endGame()
            return
        }

        enemyComet.y += enemyComet.enemyCometVelocity
        if (enemyComet.y >= screenHeight) {
            enemyComet.resetEnemyComet()
        }

        if (comet.isAlive) {
            val maxCometX = maxOf(0, screenWidth - comet.getCometWidth())
            comet.x = comet.x.coerceIn(0, maxCometX)
            canvas.drawBitmap(comet.getComet(), comet.x.toFloat(), comet.y.toFloat(), null)
        }

        for (i in cometShots.indices.reversed()) {
            val shot = cometShots[i]
            shot.y -= 15
            canvas.drawBitmap(shot.getShot(), shot.x.toFloat(), shot.y.toFloat(), null)

            if (shot.x in enemyComet.x..(enemyComet.x + enemyComet.getEnemyCometWidth()) &&
                shot.y in enemyComet.y..(enemyComet.y + enemyComet.getEnemyCometHeight())
            ) {
                points += 100
                cometShots.removeAt(i)
                val expBmp = BitmapFactory.decodeResource(context.resources, R.drawable.explosion)
                val explosionX = enemyComet.x + enemyComet.getEnemyCometWidth() / 2 - expBmp.width / 2
                val explosionY = enemyComet.y + enemyComet.getEnemyCometHeight() / 2 - expBmp.height / 2
                explosions.add(Explosion(context, explosionX, explosionY))
                enemyComet.resetEnemyComet()
            } else if (shot.y < 0) {
                cometShots.removeAt(i)
            }
        }

        if (enemyComet.x in comet.x..(comet.x + comet.getCometWidth()) &&
            (enemyComet.y + enemyComet.getEnemyCometHeight()) >= comet.y
        ) {
            life--
            val expBmp = BitmapFactory.decodeResource(context.resources, R.drawable.explosion)
            val explosionX = comet.x + comet.getCometWidth() / 2 - expBmp.width / 2
            val explosionY = comet.y + comet.getCometHeight() / 2 - expBmp.height / 2
            explosions.add(Explosion(context, explosionX, explosionY))
            enemyComet.resetEnemyComet()
        }

        for (i in explosions.indices.reversed()) {
            val exp = explosions[i]
            canvas.drawBitmap(exp.getExplosion(exp.explosionFrame), exp.x.toFloat(), exp.y.toFloat(), null)
            exp.explosionFrame++
            if (exp.explosionFrame >= 8) {
                explosions.removeAt(i)
            }
        }

        canvas.drawBitmap(enemyComet.getEnemyComet(), enemyComet.x.toFloat(), enemyComet.y.toFloat(), null)

        if (!paused) {
            handler?.postDelayed(runnable, UPDATE_MILLIS)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                if (cometShots.size < 3) {
                    val shot = Shot(context, comet.x + comet.getCometWidth() / 2, comet.y)
                    cometShots.add(shot)
                }
            }
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                comet.x = event.x.toInt() - comet.getCometWidth() / 2
            }
        }
        invalidate()
        return true
    }

    private val runnable = Runnable { invalidate() }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        handler = Handler()
        handler?.post(runnable)
    }

    override fun onDetachedFromWindow() {
        handler?.removeCallbacks(runnable)
        handler = null
        super.onDetachedFromWindow()
    }

    private fun endGame() {
        context?.let {
            saveHighScoreIfLoggedIn(points)

            val intent = Intent(it, GameOver::class.java).apply {
                putExtra("points", points)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            it.startActivity(intent)
            (it as? Activity)?.finish()
        }
    }

    private fun saveHighScoreIfLoggedIn(score: Int) {
        val userManager = UserManager(context)
        if (userManager.isUserLoggedIn()) {
            val userId = userManager.getCurrentUserId()
            if (userId != -1) {
                Handler(Looper.getMainLooper()).post {
                    (context as? AppCompatActivity)?.lifecycleScope?.launch {
                        try {
                            val database = DatabaseProvider.getDatabase(context)
                            val currentBest = database.highScoreDao().getHighestScoreByUserId(userId) ?: 0

                            if (score > currentBest) {
                                val highScore = HighScore(
                                    userId = userId,
                                    score = score
                                )
                                database.highScoreDao().insert(highScore)
                            }
                        } catch (e: Exception) {
                            Log.e("SpaceShooter", "Greška pri spremanju high score: ${e.message}")
                        }
                    }
                }
            }
        }
    }
}