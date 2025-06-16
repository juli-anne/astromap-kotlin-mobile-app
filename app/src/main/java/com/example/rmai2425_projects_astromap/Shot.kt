package com.example.rmai2425_projects_astromap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Shot(context: Context, var x: Int, var y: Int) {
    private val shot: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(context.resources, R.drawable.shot),
        70, 70, true
    )
    fun getShot(): Bitmap = shot
}
