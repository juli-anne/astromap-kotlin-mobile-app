package com.example.rmai2425_projects_astromap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.adapters.PlanetAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Planet
import kotlinx.coroutines.launch

class PlanetsFragment : Fragment() {

    private lateinit var planetAdapter: PlanetAdapter
    private lateinit var recyclerView: RecyclerView
    private val planetsList: MutableList<Planet> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_planets, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)


        val db = DatabaseProvider.getDatabase(requireContext())
        val dao = db.entitiesDao()

        lifecycleScope.launch {

            planetsList.clear()

            val planets = dao.getAllPlanets()

            val uniquePlanets = planets.distinctBy { it.id }


            planetsList.addAll(uniquePlanets)


            planetAdapter = PlanetAdapter(planetsList)
            recyclerView.adapter = planetAdapter
        }

        return rootView
    }
}
















