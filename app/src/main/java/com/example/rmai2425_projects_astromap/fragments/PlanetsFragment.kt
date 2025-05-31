package com.example.rmai2425_projects_astromap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.adapters.PlanetAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Planet
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanetsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var planetAdapter: PlanetAdapter
    private lateinit var userManager: UserManager
    private var planetList: List<Planet> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_planets, container, false)

        userManager = UserManager(requireContext())
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
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            planetList = dao.getAllPlanets()
        }

        planetAdapter = PlanetAdapter(
            planetList,
            userManager.isLoggedIn()
        ) { planetName ->
            showCompletionMessage("Naučili ste sve o planetu $planetName!")
        }

        recyclerView.adapter = planetAdapter
    }

    private fun showCompletionMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
