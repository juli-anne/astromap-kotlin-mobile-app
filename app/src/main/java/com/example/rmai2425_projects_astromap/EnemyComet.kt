package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Random

class EnemyComet(private val context: Context) {
    private var enemyComet: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.vesta)
    var x: Int = 0
    var y: Int = 0
    var enemyCometVelocity: Int = 0
    private val random = Random()

    init {
        val scaledWidth = 200
        val scaledHeight = 200
        enemyComet = Bitmap.createScaledBitmap(enemyComet, scaledWidth, scaledHeight, true)
        resetEnemyComet()
    }

    fun getEnemyComet(): Bitmap = enemyComet
    fun getEnemyCometWidth(): Int = enemyComet.width
    fun getEnemyCometHeight(): Int = enemyComet.height

    fun resetEnemyComet() {
        val width = SpaceShooter.screenWidth
        x = if (width > enemyComet.width) {
            random.nextInt(width - enemyComet.width)
        } else {
            0
        }
        y = -enemyComet.height
        enemyCometVelocity = 8 + random.nextInt(5)
    }
}