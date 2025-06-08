package com.example.rmai2425_projects_astromap

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GameFragment : AppCompatActivity() {
    /*lateinit var btnStartGame: ImageView*/
    private lateinit var linearLayout: LinearLayout

     fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_game, container, false)
        linearLayout = view.findViewById(R.id.btnStartGame)
        return view
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnStartGame = findViewById(R.id.btnStartGame)
        btnStartGame.setOnClickListener {
            val intent = Intent(this, GameOver::class.java)
            startActivity(intent)
        }

    }*/

}