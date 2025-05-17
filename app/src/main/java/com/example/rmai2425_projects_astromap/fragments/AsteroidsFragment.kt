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
import com.example.rmai2425_projects_astromap.adapters.AsteroidAdapter
import com.example.rmai2425_projects_astromap.database.Asteroid
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AsteroidsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var asteroidAdapter: AsteroidAdapter
    private var asteroidList: List<Asteroid> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroids, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_asteroid)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            insertDefaultAsteroidsIfNeeded()
            loadAsteroidsFromDatabase()
        }
    }

    private suspend fun insertDefaultAsteroidsIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existingAsteroids = dao.getAllAsteroidi()
            val existingNames = existingAsteroids.map { it.ime.lowercase() }

            val defaultAsteroids = listOf(
                Asteroid(
                    ime = "Ceres",
                    kratkiOpis = "Ceres je najveći objekt u asteroidnom pojasu i jedini patuljasti planet unutar ovog područja.",
                    detaljanOpis = "Ceres je prvi asteroid otkriven 1801. godine i dugo je smatran asteroidom sve do 2006. godine kada je re-kategoriziran kao patuljasti " +
                            "planet. Ima svoj vlastiti ledeni pokrov i u njegovim unutrašnjim dijelovima možda se nalaze podzemna jezera tekuće vode. Misija NASA-e " +
                            "'Dawn' pružila je detaljne slike i podatke o Ceresu, otkrivši fascinantne značajke kao što su svjetleće mrlje u kraterima, koje mogu " +
                            "biti ostaci slane vode.",
                    smjestaj = "Asteroidni pojas između Marsa i Jupitera",
                    sastav = "Stijene, led"
                ),
                Asteroid(
                    ime = "Vesta",
                    kratkiOpis = "Vesta je drugi po veličini objekt u asteroidnom pojasu i jedini asteroid proučavan misijom 'Dawn'.",
                    detaljanOpis = "Vesta je prekrivena velikim udubljenjima, koja su posljedica sudara s drugim objektima. Zanimljiva je zbog svoje geološke povijesti, " +
                            "a vjeruje se da je dio njene površine nastao kroz sudare. Vesta posjeduje složenu unutrašnju strukturu, s jednim velikim jezerom " +
                            "lave u svom središtu.",
                    smjestaj = "Asteroidni pojas između Marsa i Jupitera",
                    sastav = "Stijene, minerali"
                ),
                Asteroid(
                    ime = "Eros",
                    kratkiOpis = "Eros je asteroid tipa S, poznat po tome što je bio prva velika misija koja je poslala svemirski brod na njegovu površinu.",
                    detaljanOpis = "Eros je otkriven 1898. godine i od tada je predmet brojnih istraživanja. NASA-ina misija 'NEAR Shoemaker' 2000. godine bila je prva " +
                            "koja je uspješno orbitirala i sletjela na površinu asteroida Eros, pružajući nevjerojatne podatke o njegovoj strukturi i sastavu.",
                    smjestaj = "Unutarnji Sunčev sustav",
                    sastav = "Stijene, metali"
                ),
                Asteroid(
                    ime = "Itokawa",
                    kratkiOpis = "Itokawa je mali asteroid tipa S, poznat po svojoj neobičnoj, duguljastoj formi.",
                    detaljanOpis = "Otkriven je 1998. godine, a najpoznatiji je po tome što je bio ciljana destinacija misije japanske svemirske agencije JAXA, " +
                            "'Hayabusa', koja je 2005. godine poslala sondu na asteroid i vratila uzorke s njegove površine. Sonda 'Hayabusa' dala je prvi " +
                            "uspješan povrat uzoraka s asteroida.",
                    smjestaj = "Unutarnji Sunčev sustav",
                    sastav = "Stijene, minerali"
                ),
                Asteroid(
                    ime = "Pallas",
                    kratkiOpis = "Pallas je treći po veličini asteroid u Sunčevom sustavu i ima nezemaljski, nepravilni oblik.",
                    detaljanOpis = "Otkriven je 1802. godine, a zbog svojih velikih dimenzija bio je jedan od prvih objekata koji su prepoznati kao asteroidi. Pallas " +
                            "je među najneobičnijim asteroidima zbog svog složenog orbitalnog kretanja i specifične reflektivnosti koja se razlikuje od drugih " +
                            "objekata u asteroidnom pojasu.",
                    smjestaj = "Asteroidni pojas između Marsa i Jupitera",
                    sastav = "Stijene, minerali"
                )
            )

            val toInsert = defaultAsteroids.filter {
                it.ime.lowercase() !in existingNames
            }

            for (asteroid in toInsert) {
                dao.insertAsteroid(asteroid)
            }
        }
    }

    private suspend fun loadAsteroidsFromDatabase() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            asteroidList = dao.getAllAsteroidi()
        }

        asteroidAdapter = AsteroidAdapter(asteroidList)
        recyclerView.adapter = asteroidAdapter
    }
}
