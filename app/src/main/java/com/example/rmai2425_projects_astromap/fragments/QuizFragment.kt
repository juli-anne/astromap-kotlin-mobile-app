package com.example.rmai2425_projects_astromap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.utils.QuizCategory
import com.example.rmai2425_projects_astromap.adapters.QuizCategoryAdapter

class QuizFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var quizAdapter: QuizCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        recyclerView = view.findViewById(R.id.recyclerview_quiz)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val quizCategories = listOf(
            QuizCategory("Planeti", "Testiraj svoje znanje o planetima Sunčevog sustava", R.drawable.planets_quiz),
            QuizCategory("Sunce", "Testiraj svoje znanje o našoj zvijezdi", R.drawable.sun_quiz),
            QuizCategory("Mjeseci", "Testiraj svoje znanje o mjesecima Sunčevog sustava", R.drawable.moons_quiz),
            QuizCategory("Asteroidi", "Testiraj svoje znanje o asteroidima", R.drawable.asteroids_quiz),
            QuizCategory("Kometi", "Testiraj svoje znanje o kometima", R.drawable.comets_quiz),
            QuizCategory("Objekti", "Testiraj svoje znanje o objektima Sunčevog sustava", R.drawable.objects_quiz),
            QuizCategory("Zviježđa", "Testiraj svoje znanje o zviježđima", R.drawable.constellations_quiz)
        )

        quizAdapter = QuizCategoryAdapter(quizCategories)
        recyclerView.adapter = quizAdapter

        return view
    }
}