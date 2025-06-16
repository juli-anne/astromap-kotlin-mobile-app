package com.example.rmai2425_projects_astromap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rmai2425_projects_astromap.activities.MainActivity

class GameOver : AppCompatActivity() {

    private lateinit var tvPoints: TextView
    private lateinit var btnRestart: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)

        tvPoints = findViewById(R.id.tvPoints)
        btnRestart = findViewById(R.id.btnRestart)
        btnExit = findViewById(R.id.btnExit)

        val points = intent.getIntExtra("points", 0)
        tvPoints.text = "Points: $points"
        btnRestart = findViewById(R.id.btnRestart)
        btnRestart.setOnClickListener {
            restart()
        }
        btnExit = findViewById(R.id.btnExit)
        btnExit.setOnClickListener {
            exit()
        }
    }

    fun restart() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("restart_game", true)
        startActivity(intent)
        finish()
    }

    fun exit() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("exit_game", true)
        startActivity(intent)
        finish()
    }
}
