package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Random

class Comet(private val context: Context) {
    private var comet: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.earth)
    var x: Int = 0
    var y: Int = 0
    var isAlive: Boolean = true
    var cometVelocity: Int = 0
    private val random = Random()

    init {
        requireNotNull(comet) { "Bitmap resource R.drawable.earth not found!" }
        require(comet.width > 0 && comet.height > 0) { "Comet image has invalid dimensions!" }
        comet = Bitmap.createScaledBitmap(comet, 100, 100, true)
        resetComet()
    }

    fun getComet(): Bitmap = comet
    fun getCometWidth(): Int = comet.width

    fun resetComet() {
        x = (SpaceShooter.screenWidth - comet.width) / 2
        y = SpaceShooter.screenHeight - comet.height - 30
        cometVelocity = 0
    }
}