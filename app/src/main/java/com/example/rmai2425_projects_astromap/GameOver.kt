package com.example.rmai2425_projects_astromap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOver : AppCompatActivity() {

    private lateinit var tvPoints: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)

        val points = intent.extras?.getInt("points") ?: 0
        tvPoints = findViewById(R.id.tvPoints)
        tvPoints.text = points.toString()
    }

    fun restart(v: View) {
        val intent = Intent(this, GameFragment::class.java)
        startActivity(intent)
        finish()
    }

    fun exit(v: View) {
        finish()
    }
}
