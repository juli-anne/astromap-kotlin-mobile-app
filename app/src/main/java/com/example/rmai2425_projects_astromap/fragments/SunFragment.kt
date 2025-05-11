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
import com.example.rmai2425_projects_astromap.adapters.SunAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Sunce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SunFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sunAdapter: SunAdapter
    private var sunList: List<Sunce> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sun, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_sun)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            insertDefaultSunceIfNeeded()
            loadSunsFromDatabase()
        }
    }

    private suspend fun insertDefaultSunceIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existing = dao.getAllSunca()
            val alreadyExists = existing.any { it.ime.equals("Sunce", ignoreCase = true) }

            if (!alreadyExists) {
                val defaultSunce = Sunce(
                    ime = "Sunce",
                    kratkiOpis = "Naša najbliža zvijezda i izvor svjetlosti.",
                    detaljanOpis = "Sunce je zvijezda spektralnog tipa G2V koja se nalazi u središtu Sunčevog sustava.",
                    povrsinskaTemperatura = "5,500 °C",
                    temperaturaJezgre = "15,000,000 °C",
                    promjer = 1_392_700.0,
                    masa = 1.989e30,
                    sastav = "70% vodik, 28% helij, 2% ostali elementi"
                )
                dao.insertSunce(defaultSunce)
            }
        }
    }

    private suspend fun loadSunsFromDatabase() {
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.entitiesDao()

        withContext(Dispatchers.IO) {
            sunList = dao.getAllSunca()
        }

        sunAdapter = SunAdapter(sunList)
        recyclerView.adapter = sunAdapter
    }
}
