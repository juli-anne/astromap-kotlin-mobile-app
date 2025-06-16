package com.example.rmai2425_projects_astromap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.SpaceShooter

class GameFragment : Fragment() {
    private lateinit var btnStartGame: Button
    private lateinit var gameContainer: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_game, container, false)
        btnStartGame = view.findViewById(R.id.btnStartGame)
        gameContainer = view.findViewById(R.id.gameContainer)
        btnStartGame.setOnClickListener {
            startGame()
        }
        return view
    }

    private fun startGame() {
        btnStartGame.visibility = View.GONE
        val spaceShooter = SpaceShooter(requireContext())
        gameContainer.addView(spaceShooter)
    }
}
