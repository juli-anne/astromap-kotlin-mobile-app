package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Explosion(context: Context, val x: Int, val y: Int) {

    private val explosion: Array<Bitmap> = Array(9) {
        BitmapFactory.decodeResource(context.resources, R.drawable.explosion)
    }

    var explosionFrame: Int = 0

    fun getExplosion(frame: Int): Bitmap {
        return explosion[frame]
    }
}
