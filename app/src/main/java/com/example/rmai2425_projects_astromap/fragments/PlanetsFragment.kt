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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanetsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var planetAdapter: PlanetAdapter
    private var planetList: List<Planet> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_planets, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            loadPlanetsFromDatabase()
        }
    }

    private suspend fun loadPlanetsFromDatabase() {
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.entitiesDao()

        withContext(Dispatchers.IO) {
            planetList = dao.getAllPlanets()
        }

        planetAdapter = PlanetAdapter(planetList)
        recyclerView.adapter = planetAdapter
    }
}
















