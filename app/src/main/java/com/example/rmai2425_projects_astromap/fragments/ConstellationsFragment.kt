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
import com.example.rmai2425_projects_astromap.adapters.ConstellationAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Zvijezdje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstellationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var constellationAdapter: ConstellationAdapter
    private var constellationList: List<Zvijezdje> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_constellations, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_constellation)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            removeDuplicateConstellations()
            insertDefaultConstellationsIfNeeded()
            loadConstellationsFromDatabase()
        }
    }

    private fun getBaseName(name: String): String {
        val normalized = name.substringBefore("(").trim().lowercase()
            .replace("š", "s")
            .replace("ć", "c")
            .replace("č", "c")
            .replace("ž", "z")
            .replace("đ", "d")
            .replace(" ", "")

        val map = mapOf(
            "pjescanisat" to "pescanisat",
            "pescanisat" to "pescanisat",
            "hourglass" to "pescanisat",
            "lavi" to "leo",
            "lav" to "leo",
            "leo" to "leo"
        )
        return map[normalized] ?: normalized
    }

    private suspend fun removeDuplicateConstellations() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val all = dao.getAllZvijezdja()
            val grouped = all.groupBy { getBaseName(it.imeHr) }

            grouped.values.forEach { duplicates ->
                if (duplicates.size > 1) {

                    duplicates.drop(1).forEach { dao.deleteZvijezdje(it) }
                }
            }
        }
    }

    private suspend fun insertDefaultConstellationsIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existing = dao.getAllZvijezdja()
            val existingNames = existing.map { getBaseName(it.imeHr) }

            val defaultConstellations = listOf(
                Zvijezdje(
                    imeHr = "Lovac",
                    imeLat = "Orion",
                    pozicija = "Orion je jedno od najpoznatijih i najsvjetlijih zviježđa na nebu, prepoznaje se po 'Orionovom pojasu', grupi od tri svijetle zvijezde " +
                            "koje formiraju središnji dio.",
                    znacaj = "Poznat u mnogim kulturama, u starom Egiptu povezan s bogom Osirisom. Orionova maglica (M42) je jedno od najbližih područja stvaranja novih zvijezda.",
                    svjetleZvijezde = "Betelgeuse, Rigel"
                ),
                Zvijezdje(
                    imeHr = "Veliki Medvjed (Ursa Major)",
                    imeLat = "Ursa Major",
                    pozicija = "Jedno od najvećih i najprepoznatljivijih zviježđa na sjevernom nebu. Najpoznatiji dio je Velika Kolica, grupa sedam svijetlih zvijezda.",
                    znacaj = "Važan za navigaciju jer je Polarna zvijezda (u zviježđu Ursa Minor) usmjerena prema Velikom Medvjedu.",
                    svjetleZvijezde = "Dubhe, Merak"
                ),
                Zvijezdje(
                    imeHr = "Maleni Medvjed (Ursa Minor)",
                    imeLat = "Ursa Minor",
                    pozicija = "Smješten bliže sjevernom polu, najpoznatiji dio je Polarna zvijezda (Polaris).",
                    znacaj = "Polarna zvijezda je bila ključna za navigaciju jer pokazuje pravi smjer prema sjeveru.",
                    svjetleZvijezde = "Polaris"
                ),
                Zvijezdje(
                    imeHr = "Kasiopeja",
                    imeLat = "Cassiopeia",
                    pozicija = "Jedno od najpoznatijih zviježđa u sjevernoj hemisferi, lako se prepoznaje po obliku slova 'W' ili 'M'.",
                    znacaj = "Poznata u mitologiji kao kraljica Etiopije, povezano s njezinom pričom.",
                    svjetleZvijezde = "Schedar, Caph, Ruchbah"
                ),
                Zvijezdje(
                    imeHr = "Lavi",
                    imeLat = "Leo",
                    pozicija = "Jedno od najsvjetlijih zviježđa na nebu, prepoznaje se po obliku lavljeg tijela.",
                    znacaj = "Povezano s mnogim mitologijama, simbol moći i snage.",
                    svjetleZvijezde = "Regulus, Algieba"
                ),
                Zvijezdje(
                    imeHr = "Vaga",
                    imeLat = "Libra",
                    pozicija = "Zviježđe Zodijaka, smješteno između Škorpiona i Jarca.",
                    znacaj = "Često povezana s pravdom, predstavlja vagu boginje Temide, simbol pravednosti.",
                    svjetleZvijezde = "Zuben el-Akrab, Zuben el-Shamali"
                ),
                Zvijezdje(
                    imeHr = "Škorpion",
                    imeLat = "Scorpius",
                    pozicija = "Vrlo prepoznatljivo zviježđe na jugoistočnom dijelu neba.",
                    znacaj = "U mitologiji povezano s opasnostima i borbom protiv zla.",
                    svjetleZvijezde = "Antares"
                ),
                Zvijezdje(
                    imeHr = "Strijelac",
                    imeLat = "Sagittarius",
                    pozicija = "Zviježđe Zodijaka, prepoznaje se po obliku strijelca s lukom.",
                    znacaj = "Povezan s lovom i osvajanjima, u mitologiji predstavljalo centaura.",
                    svjetleZvijezde = "Kaus Australis"
                ),
                Zvijezdje(
                    imeHr = "Kentaur",
                    imeLat = "Centaurus",
                    pozicija = "Jedno od najvećih zviježđa na južnoj hemisferi.",
                    znacaj = "Sadrži dvije od najsvjetlijih zvijezda na nebu, važno u navigaciji na južnoj hemisferi.",
                    svjetleZvijezde = "Alpha Centauri, Beta Centauri"
                ),
                Zvijezdje(
                    imeHr = "Maglica pješčanog sata",
                    imeLat = "Hourglass Nebula",
                    pozicija = "Maglica u zviježđu Sagittariusa.",
                    znacaj = "Jedno od najpoznatijih područja u svemiru zbog prepoznatljive oblika, simbol svemirskih promjena.",
                    svjetleZvijezde = ""
                )
            )

            val toInsert = defaultConstellations.filter {
                getBaseName(it.imeHr) !in existingNames
            }

            if (toInsert.isNotEmpty()) {
                toInsert.forEach { dao.insertZvijezdje(it) }
            }
        }
    }

    private suspend fun loadConstellationsFromDatabase() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            constellationList = dao.getAllZvijezdja()
        }

        constellationAdapter = ConstellationAdapter(constellationList)
        recyclerView.adapter = constellationAdapter
    }
}
