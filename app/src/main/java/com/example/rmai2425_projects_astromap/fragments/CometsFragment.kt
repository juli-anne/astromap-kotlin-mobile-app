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
import com.example.rmai2425_projects_astromap.adapters.CometAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Komet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CometsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cometAdapter: CometAdapter
    private var cometList: List<Komet> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comets, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_comet)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            insertDefaultCometsIfNeeded()
            loadCometsFromDatabase()
        }
    }

    private suspend fun insertDefaultCometsIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existingComets = dao.getAllKometi()
            val existingNames = existingComets.map { it.ime.lowercase() }

            val defaultComets = listOf(
                Komet(
                    ime = "Halleyjev komet",
                    kratkiOpis = "Halleyjev komet je jedan od najpoznatijih kometa u Sunčevom sustavu, a najpoznatiji je zbog toga što je jedini komet koji je vidljiv " +
                            "iz Zemlje s povremenim intervalima od oko 76 godina.",
                    orbitalniPeriod = 76,
                    posljednjiPerihel = "1986.",
                    sljedeciPerihel = "2061.",
                    velicinaJezgre = 15.0
                ),
                Komet(
                    ime = "Hale-Bopp",
                    kratkiOpis = "Hale-Bopp je jedan od najsvjetlijih kometa ikada zabilježenih. Prošao je kroz Sunčev sustav 1997. godine i bio je vidljiv golim okom, " +
                            "čak i iz gradova.",
                    orbitalniPeriod = 2533,
                    posljednjiPerihel = "1997.",
                    sljedeciPerihel = "4530.",
                    velicinaJezgre = 40.0
                ),
                Komet(
                    ime = "Neowise",
                    kratkiOpis = "Neowise je komet koji je postao vrlo poznat tijekom 2020. godine, jer je bio jasno vidljiv golim okom s površine Zemlje.",
                    orbitalniPeriod = 6766,
                    posljednjiPerihel = "2020.",
                    sljedeciPerihel = "8786.",
                    velicinaJezgre = 5.0
                ),
                Komet(
                    ime = "Tempel 1",
                    kratkiOpis = "Tempel 1 je komet koji je postao poznat kada je NASA-ina misija 'Deep Impact' 2005. godine ispalila projektile na njegovu površinu " +
                            "kako bi analizirali materijal ispod nje.",
                    orbitalniPeriod = 6,
                    posljednjiPerihel = "2016.",
                    sljedeciPerihel = "2022.",
                    velicinaJezgre = 14.0
                ),
                Komet(
                    ime = "Enckeov komet",
                    kratkiOpis = "Enckeov komet je komet koji ima najkraći poznati orbitalni period od svih kometa, jer mu je potrebno samo oko 3,3 godine da obiđe Sunce.",
                    orbitalniPeriod = 3,
                    posljednjiPerihel = "2017.",
                    sljedeciPerihel = "2020.",
                    velicinaJezgre = 4.8
                )
            )

            val toInsert = defaultComets.filter {
                it.ime.lowercase() !in existingNames
            }

            for (comet in toInsert) {
                dao.insertKomet(comet)
            }
        }
    }

    private suspend fun loadCometsFromDatabase() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            cometList = dao.getAllKometi()
        }

        cometAdapter = CometAdapter(cometList)
        recyclerView.adapter = cometAdapter
    }
}
