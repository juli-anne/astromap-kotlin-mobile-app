package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Shot(context: Context, var x: Int, var y: Int) {

    private val shot: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.shot)

    fun getShot(): Bitmap {
        return shot
    }
}
