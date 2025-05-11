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
import com.example.rmai2425_projects_astromap.database.AstroMapDatabase
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
        inflater: LayoutInflater, container: ViewGroup?,
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
            insertDefaultPlanetsIfNeeded()
            loadPlanetsFromDatabase()
        }
    }

    private suspend fun insertDefaultPlanetsIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existingPlanets = dao.getAllPlanets()
            val existingNames = existingPlanets.map { it.ime.lowercase() }

            val defaultPlanets = listOf(
                Planet(
                    ime = "Merkur",
                    kratkiOpis = "Merkur je najmanji planet u Sunčevom sustavu i najbliži Suncu. Njegova površina je prekrivena kraterima, slična Mjesecu.",
                    detaljanOpis = "Merkur je najbrži planet u Sunčevom sustavu, jer obiđe Sunce za samo 88 dana. Ima gotovo nikakvu atmosferu, pa temperature na njegovoj " +
                            "površini variraju od +430°C na sunčanoj strani do -180°C na tamnoj strani. Merkur ima mnoge sličnosti s Mjesecom, uključujući mnoge " +
                            "kratere, ali nema mjesece. Zbog svoje blizine Suncu, njegovo kretanje je izuzetno teško pratiti s Zemlje.",
                    povrsinskaTemperaturaDan = "+430°C",
                    povrsinskaTemperaturaNoc = "-180°C",
                    promjer = 4880.0,
                    imaMjesec = false
                ),
                Planet(
                    ime = "Venera",
                    kratkiOpis = "Venera je drugi planet od Sunca i najviše nalikuje Zemlji po veličini, no ima izuzetno gustu atmosferu.",
                    detaljanOpis = "Venera ima najgušću atmosferu u Sunčevom sustavu, koja se sastoji uglavnom od ugljičnog dioksida. Zbog toga se stvara izuzetno snažan efekt staklenke, zbog kojeg je temperatura na površini vrlo visoka, dosežući i do 460°C. Atmosfera je toliko gusta da je površina planeta potpuno nevidljiva s površine kroz teleskope. Venera ima mnoge vulkane i planine, a rotira u suprotnom smjeru od većine planeta u Sunčevom sustavu, zbog čega dan na Veneri traje duže od godine.",
                    povrsinskaTemperaturaDan = "460°C",
                    povrsinskaTemperaturaNoc = "460°C",
                    promjer = 12104.0,
                    imaMjesec = false
                ),
                Planet(
                    ime = "Zemlja",
                    kratkiOpis = "Zemlja je treći planet od Sunca i jedini poznat planet na kojem postoji život.",
                    detaljanOpis = "Zemlja je jedini planet u Sunčevom sustavu koji ima tekuću vodu na svojoj površini. Njezina atmosfera sadrži 21% kisika, što omogućava život. Zemlja je ujedno jedini planet koji ima tekuću vodu, stabilnu klimu i složenu biotsku zajednicu. Na Zemlji postoje kontinenti i oceani, a ljudi, životinje i biljke čine složene ekosustave. Zemlja se okreće oko Sunca u 365 dana, a rotira oko svoje osi svakih 24 sata, što uzrokuje izmjenu dana i noći.",
                    povrsinskaTemperaturaDan = "15°C",
                    povrsinskaTemperaturaNoc = "15°C",
                    promjer = 12742.0,
                    imaMjesec = true
                ),
                Planet(
                    ime = "Mars",
                    kratkiOpis = "Mars je poznat kao Crveni planet zbog svoje crvene boje koju uzrokuje željezo u njegovoj prašini.",
                    detaljanOpis = "Mars je četvrti planet od Sunca i ima površinu prepunu prašinskih oluja, kanjona i vulkana. Atmosfera Marsa je vrlo tanka, sastoji se uglavnom od ugljičnog dioksida, a temperatura na njegovoj površini može pasti ispod -100°C. Znanstvenici vjeruju da je Mars nekada imao vodu na svojoj površini, a danas postoje znakovi da bi mogao imati podzemne rezervoare vode. Mars ima dva mala mjeseca, Phobos i Deimos, koji su vjerojatno uhvaćeni asteroidi.",
                    povrsinskaTemperaturaDan = "-63°C",
                    povrsinskaTemperaturaNoc = "-63°C",
                    promjer = 6779.0,
                    imaMjesec = true
                ),
                Planet(
                    ime = "Jupiter",
                    kratkiOpis = "Jupiter je najveći planet u Sunčevom sustavu i poznat je po svojoj masivnoj atmosferi i snažnoj gravitaciji.",
                    detaljanOpis = "Jupiter je plinoviti div, a njegova atmosfera sastoji se uglavnom od vodika i helija. Na Jupiteru se nalazi poznati Veliki crveni gori, ogromna oluja koja traje već stoljećima. Jupiter ima više od 90 mjeseca, uključujući Europa, Ganimed i Io, koji su među najvećim mjesecevima u Sunčevom sustavu. Zbog svoje ogromne mase, Jupiter ima izuzetno snažnu gravitaciju koja utječe na mnoge objekte u Sunčevom sustavu, uključujući komete i asteroide.",
                    povrsinskaTemperaturaDan = "-108°C",
                    povrsinskaTemperaturaNoc = "-108°C",
                    promjer = 139820.0,
                    imaMjesec = true
                ),
                Planet(
                    ime = "Saturn",
                    kratkiOpis = "Saturn je planet poznat po svojim spektakularnim prstenovima, koji su najljepši u Sunčevom sustavu.",
                    detaljanOpis = "Saturn je drugi po veličini planet u Sunčevom sustavu i plinoviti je div. Atmosfera Saturna sastoji se od vodika, helija i malih količina metana i amonijaka. Planetu krase prstenovi koji se sastoje od leda i prašine, a najpoznatiji prstenovi su A, B i C prsten. Saturn ima više od 80 mjeseca, uključujući Titan, drugi najveći mjesec u Sunčevom sustavu. Titan je jedini mjesec u Sunčevom sustavu koji ima gustu atmosferu, tekuću metansku kišu i tekući metan na površini.",
                    povrsinskaTemperaturaDan = "-178°C",
                    povrsinskaTemperaturaNoc = "-178°C",
                    promjer = 116460.0,
                    imaMjesec = true
                ),
                Planet(
                    ime = "Uran",
                    kratkiOpis = "Uran je plinoviti div s posebnim kutom rotacije, koji je gotovo vodoravan u odnosu na svoju orbitu.",
                    detaljanOpis = "Uran je sedmi planet od Sunca i poznat je po svom neobičnom kutu rotacije, jer se njegova osa rotacije gotovo potpuno poklapa s ravninom njegove orbite. Uranova atmosfera sastoji se od vodika, helija i metana, zbog čega planet ima plavičasto-zelenu boju. Uran je hladan planet, s najnižom poznatom temperaturom među planetima Sunčevog sustava. Uran ima 27 poznatih mjeseca, a najpoznatiji je Titania.",
                    povrsinskaTemperaturaDan = "-224°C",
                    povrsinskaTemperaturaNoc = "-224°C",
                    promjer = 50724.0,
                    imaMjesec = true
                ),
                Planet(
                    ime = "Neptun",
                    kratkiOpis = "Neptun je posljednji planet Sunčevog sustava i ima najjače vjetrove u Sunčevom sustavu.",
                    detaljanOpis = "Neptun je osmi planet od Sunca i plinoviti je div. Sastoji se od vodika, helija i metana, koji mu daje plavičastu boju. Neptun je poznat po svojim izuzetno jakim vjetrovima koji dosežu brzine do 2.400 km/h. Ovaj planet također ima ogromne oluje koje mogu trajati godinama. Neptun ima 14 mjeseca, a najpoznatiji je Triton, koji ima retrogradnu orbitu, što znači da orbitira u suprotnom smjeru od većine drugih mjeseca u Sunčevom sustavu.",
                    povrsinskaTemperaturaDan = "-214°C",
                    povrsinskaTemperaturaNoc = "-214°C",
                    promjer = 49244.0,
                    imaMjesec = true
                )
            )

            val planetsToInsert = defaultPlanets.filter {
                it.ime.lowercase() !in existingNames
            }

            if (planetsToInsert.isNotEmpty()) {
                dao.insertPlanets(planetsToInsert)
            }
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
















