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
import com.example.rmai2425_projects_astromap.adapters.ObjectAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.ObjektSuncevogSustava
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ObjectsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var objectAdapter: ObjectAdapter
    private var objectList: List<ObjektSuncevogSustava> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_objects, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_object)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            insertDefaultObjectsIfNeeded()
            loadObjectsFromDatabase()
        }
    }

    private suspend fun insertDefaultObjectsIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existingObjects = dao.getAllObjekti()
            val existingNames = existingObjects.map { it.ime.lowercase() }

            val defaultObjects = listOf(
                ObjektSuncevogSustava(
                    ime = "Pluton",
                    tip = "Patuljasti planet",
                    smjestaj = "Kuiperov pojas (izvan orbite Neptuna)",
                    opis = "Pluton je nekada bio deveti planet Sunčevog sustava, ali je 2006. godine re-kategoriziran kao patuljasti planet zbog svoje male veličine i " +
                            "neobičnog orbitiranja. Vrlo je hladan, s površinskim temperaturama od oko -230°C.",
                    zanimljivosti = "Pluton ima pet mjeseca, od kojih je najpoznatiji Charon, gotovo polovica njegove veličine. Njegova orbita je vrlo eliptična i " +
                            "presijeca orbitu Neptuna.",
                    velicina = "2,377 km",
                    sastav = "Led, stijene"
                ),
                ObjektSuncevogSustava(
                    ime = "Ceres",
                    tip = "Patuljasti planet",
                    smjestaj = "Asteroidni pojas između Marsa i Jupitera",
                    opis = "Ceres je najveći objekt u asteroidnom pojasu i jedini patuljasti planet unutar ovog područja. Prvobitno je bio klasificiran kao asteroid, " +
                            "ali je 2006. re-kategoriziran kao patuljasti planet.",
                    zanimljivosti = "Ceres sadrži velike količine leda, a postoje naznake da ispod površine možda postoji tekući vodeni ocean. Misija NASA-e Dawn " +
                            "otkrila je svjetleće mrlje u nekim kraterima.",
                    velicina = "940 km",
                    sastav = "Stijene, led"
                ),
                ObjektSuncevogSustava(
                    ime = "Meteoroid",
                    tip = "Mali kameniti ili metalni objekt",
                    smjestaj = "Kruži Sunčevim sustavom",
                    opis = "Meteoroidi su manji komadići svemirske prašine i kamenja veličine od nekoliko mikrometara do nekoliko metara. Najčešće potječu od kometa " +
                            "ili asteroida.",
                    zanimljivosti = "Kada meteoroid uđe u Zemljinu atmosferu i izgori, postaje meteor ('zvijezda padalica').",
                    velicina = "od mikrometara do nekoliko metara",
                    sastav = "Stijene, metal"
                ),
                ObjektSuncevogSustava(
                    ime = "Meteor",
                    tip = "Svjetlosna pojava",
                    smjestaj = "Zemljina atmosfera",
                    opis = "Meteor je meteoroid koji ulazi u Zemljinu atmosferu i izgara zbog trenja, stvarajući svjetlosnu pojavu poznatu kao 'zvijezda padalica'.",
                    zanimljivosti = "Većina meteora izgarava visoko u atmosferi, ali oni koji prežive postaju meteoriti.",
                    velicina = "Varijabilna",
                    sastav = "Stijene, metal"
                ),
                ObjektSuncevogSustava(
                    ime = "Meteorit",
                    tip = "Kameniti ili metalni objekt",
                    smjestaj = "Površina Zemlje",
                    opis = "Meteoriti su meteoroidi koji prežive prolazak kroz atmosferu i padnu na površinu Zemlje.",
                    zanimljivosti = "Najveći poznati meteorit je Hoba u Namibiji, težak oko 60 tona.",
                    velicina = "od grama do desetaka tona",
                    sastav = "Stijene, metal"
                ),
                ObjektSuncevogSustava(
                    ime = "Svemirska prašina",
                    tip = "Mikroskopske čestice",
                    smjestaj = "Cijeli Sunčev sustav",
                    opis = "Svemirska prašina su sitne čestice koje plutaju kroz svemir. Mogu biti prašina, dim ili manji komadići leda i stijena.",
                    zanimljivosti = "Svemirska prašina može pomoći u stvaranju novih zvijezda i planeta te sudjeluje u stvaranju meteora i prstenova planeta.",
                    velicina = "Mikrometarska",
                    sastav = "Prašina, led, stijene"
                )
            )

            val toInsert = defaultObjects.filter {
                it.ime.lowercase() !in existingNames
            }

            for (obj in toInsert) {
                dao.insertObjekt(obj)
            }
        }
    }

    private suspend fun loadObjectsFromDatabase() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            objectList = dao.getAllObjekti()
        }

        objectAdapter = ObjectAdapter(objectList)
        recyclerView.adapter = objectAdapter
    }
}
