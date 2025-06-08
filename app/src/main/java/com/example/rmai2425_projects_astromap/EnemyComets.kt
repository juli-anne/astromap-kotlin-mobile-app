package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class EnemyComet(private val context: Context) {

    private var enemyComet: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.vesta)

    var x: Int = 0
    var y: Int = 0
    var enemyCometVelocity: Int = 0
    private val random = java.util.Random()

    init {
        resetEnemyComet()
    }

    fun getEnemyComet(): Bitmap {
        return enemyComet
    }

    fun getEnemyCometWidth(): Int {
        return enemyComet.width
    }

    fun getEnemyCometHeight(): Int {
        return enemyComet.height
    }

    private fun resetEnemyComet() {
        x = 200 + random.nextInt(400)
        y = 0
        enemyCometVelocity = 14 + random.nextInt(10)
    }
}
