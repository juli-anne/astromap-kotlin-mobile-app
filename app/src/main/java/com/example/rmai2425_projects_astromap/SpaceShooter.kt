package com.example.rmai2425_projects_astromap

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Handler
import android.view.*
import java.util.*
import kotlin.collections.ArrayList

class SpaceShooter(context: Context) : View(context) {

    private val background: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.background)
    private val lifeImage: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.life)
    private var handler: Handler? = Handler()
    private val UPDATE_MILLIS = 30L

    companion object {
        var screenWidth: Int = 0
        var screenHeight: Int = 0
    }

    private var points = 0
    private var life = 3
    private val TEXT_SIZE = 80
    private var paused = false

    private val comet = Comet(context)
    private val enemyComet = EnemyComet(context)
    private val random = Random()

    private val enemyCometShots = ArrayList<Shot>()
    private val cometShots = ArrayList<Shot>()
    private val explosions = ArrayList<Explosion>()

    private var enemyExplosion = false
    private var enemyCometShotAction = false
    private var explosion: Explosion? = null

    private val scorePaint: Paint = Paint().apply {
        color = Color.RED
        textSize = TEXT_SIZE.toFloat()
        textAlign = Paint.Align.LEFT
    }

    private val runnable = Runnable { invalidate() }

    init {
        val display: Display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(background, 0f, 0f, null)
        canvas.drawText("Pt: $points", 0f, TEXT_SIZE.toFloat(), scorePaint)

        for (i in life downTo 1) {
            canvas.drawBitmap(lifeImage, (screenWidth - lifeImage.width * i).toFloat(), 0f, null)
        }

        if (life == 0) {
            paused = true
            handler = null
            val intent = Intent(context, GameOver::class.java)
            intent.putExtra("points", points)
            context.startActivity(intent)
            (context as Activity).finish()
            return
        }

        enemyComet.x += enemyComet.enemyCometVelocity
        if (enemyComet.x + enemyComet.getEnemyCometWidth() >= screenWidth || enemyComet.x <= 0) {
            enemyComet.enemyCometVelocity *= -1
        }

        if (!enemyCometShotAction && enemyComet.x >= 200 + random.nextInt(400)) {
            val enemyShot = Shot(context, enemyComet.x + enemyComet.getEnemyCometWidth() / 2, enemyComet.y)
            enemyCometShots.add(enemyShot)
            enemyCometShotAction = true
        }

        if (!enemyExplosion) {
            canvas.drawBitmap(enemyComet.getEnemyComet(), enemyComet.x.toFloat(), enemyComet.y.toFloat(), null)
        }

        if (comet.isAlive) {
            comet.x = comet.x.coerceIn(0, screenWidth - comet.getCometWidth())
            canvas.drawBitmap(comet.getComet(), comet.x.toFloat(), comet.y.toFloat(), null)
        }

        for (i in enemyCometShots.indices.reversed()) {
            val shot = enemyCometShots[i]
            shot.y += 15
            canvas.drawBitmap(shot.getShot(), shot.x.toFloat(), shot.y.toFloat(), null)

            if (shot.x in comet.x..(comet.x + comet.getCometWidth()) &&
                shot.y in comet.y..screenHeight
            ) {
                life--
                enemyCometShots.removeAt(i)
                explosions.add(Explosion(context, comet.x, comet.y))
            } else if (shot.y >= screenHeight) {
                enemyCometShots.removeAt(i)
            }

            if (enemyCometShots.isEmpty()) {
                enemyCometShotAction = false
            }
        }

        for (i in cometShots.indices.reversed()) {
            val shot = cometShots[i]
            shot.y -= 15
            canvas.drawBitmap(shot.getShot(), shot.x.toFloat(), shot.y.toFloat(), null)

            if (shot.x in enemyComet.x..(enemyComet.x + enemyComet.getEnemyCometWidth()) &&
                shot.y in enemyComet.y..(enemyComet.y + enemyComet.getEnemyCometHeight())
            ) {
                points++
                cometShots.removeAt(i)
                explosions.add(Explosion(context, enemyComet.x, enemyComet.y))
            } else if (shot.y <= 0) {
                cometShots.removeAt(i)
            }
        }

        for (i in explosions.indices.reversed()) {
            val exp = explosions[i]
            canvas.drawBitmap(exp.getExplosion(exp.explosionFrame), exp.x.toFloat(), exp.y.toFloat(), null)
            exp.explosionFrame++
            if (exp.explosionFrame > 8) {
                explosions.removeAt(i)
            }
        }

        if (!paused) {
            handler?.postDelayed(runnable, UPDATE_MILLIS)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x.toInt()
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                if (cometShots.size < 3) {
                    val shot = Shot(context, comet.x + comet.getCometWidth() / 2, comet.y)
                    cometShots.add(shot)
                }
            }
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                comet.x = touchX
            }
        }
        return true
    }
}
