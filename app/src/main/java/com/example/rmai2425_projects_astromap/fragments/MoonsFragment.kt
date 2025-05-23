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
import com.example.rmai2425_projects_astromap.adapters.MoonAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Mjesec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoonsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var moonAdapter: MoonAdapter
    private var moonList: List<Mjesec> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_moons, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_moon)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            loadMoonsFromDatabase()
        }
    }


    private suspend fun loadMoonsFromDatabase() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            moonList = dao.getAllMjeseci()
        }

        val allPlanets = dao.getAllPlanets()
        val planetIdToNameMap = allPlanets.associateBy({ it.id }, { it.ime })

        moonAdapter = MoonAdapter(moonList, planetIdToNameMap)
        recyclerView.adapter = moonAdapter
    }
}
