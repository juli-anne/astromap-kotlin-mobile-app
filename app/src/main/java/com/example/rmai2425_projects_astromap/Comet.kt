package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Random

class Comet(private val context: Context) {

    private var comet: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.earth)

    var x: Int = 0
    var y: Int = 0
    var isAlive: Boolean = true
    var cometVelocity: Int = 0
    private val random = Random()

    init {
        resetComet()
    }

    fun getComet(): Bitmap {
        return comet
    }

    fun getCometWidth(): Int {
        return comet.width
    }

    private fun resetComet() {
        x = random.nextInt(SpaceShooter.screenWidth)
        y = SpaceShooter.screenHeight - comet.height
        cometVelocity = 10 + random.nextInt(6)
    }
}
