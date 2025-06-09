package com.example.rmai2425_projects_astromap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rmai2425_projects_astromap.activities.MainActivity

class GameOver : AppCompatActivity() {

    private lateinit var tvPoints: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)

        val points = intent.getIntExtra("points", 0)
        tvPoints.text = points.toString()
    }

    fun restart(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("restart_game", true)
        startActivity(intent)
        finish()
    }

    fun exit(v: View) {
        finish()
    }
}
