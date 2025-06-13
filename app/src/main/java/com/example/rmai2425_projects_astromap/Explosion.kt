package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Explosion(context: Context, var x: Int, var y: Int) {
    private val explosion: Array<Bitmap> = Array(9) {
        BitmapFactory.decodeResource(context.resources, R.drawable.explosion)
    }

    val width: Int = explosion[0].width
    val height: Int = explosion[0].height

    var explosionFrame: Int = 0
    fun getExplosion(frame: Int): Bitmap = explosion[frame % explosion.size]
}
