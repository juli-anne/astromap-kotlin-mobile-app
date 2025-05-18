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
import com.example.rmai2425_projects_astromap.database.MockDataLoader.MjesecInfo
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
            insertDefaultMoonsIfNeeded()
            loadMoonsFromDatabase()
        }
    }

    private suspend fun insertDefaultMoonsIfNeeded() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()

        withContext(Dispatchers.IO) {
            val existingMoons = dao.getAllMjeseci()
            val existingNames = existingMoons.map { it.ime.lowercase() }

            val allPlanets = dao.getAllPlanets()
            val planetNameToId = allPlanets.associateBy { it.ime.lowercase() }

            val defaultMoons = listOf(
                MjesecInfo(
                    ime = "Mjesec",
                    planetIme = "Zemlja",
                    kratkiOpis = "Mjesec je jedini prirodni satelit Zemlje i najbliži objekt na nebu. Njegov površinski izgled dominira kraterima i velikim ravnicama.",
                    detaljanOpis = "Mjesec je najveći prirodni satelit u Sunčevom sustavu u odnosu na veličinu planeta kojem pripada. Iako nema atmosferu, " +
                            "na Mjesecu su prisutni veliki krateri, ravnice i planine, a najpoznatija područja su \"morske\" (mare), koja su tamnija. " +
                            "Mjesec je odgovoran za plime i oseke na Zemlji, jer gravitacija Mjeseca vuče vodu prema sebi. Također, Mjesec ima stalnu " +
                            "temperaturu, od -173°C noću do +127°C danju. Mjesec ima faze (novi mjesec, prva četvrt, pun mjesec) zbog svoje rotacije oko Zemlje.",
                    velicina = 3474.0,
                    zanimljivosti = "Mjesec je jedini objekt u svemiru na kojem su ljudi hodali. Misija Apollo 11 1969. godine, Neil Armstrong i Buzz Aldrin, " +
                            "prvi su ljudi koji su kročili na Mjesec."
                ),
                MjesecInfo(
                    ime = "Europa",
                    planetIme = "Jupiter",
                    kratkiOpis = "Europa je mjesec Jupitera i jedno od najsvjetlijih tijela u Sunčevom sustavu. Poznata je po svom ledenom pokrovu i mogućem oceanu " +
                            "ispod površine.",
                    detaljanOpis = "Europa je prekrivena slojem leda debljine nekoliko kilometara, a pod njim se vjeruje da postoji veliki slani ocean. Znanstvenici " +
                            "smatraju da bi ovaj ocean mogao imati uvjete pogodne za život, jer se ispod površine može nalaziti topla voda zbog geotermalne " +
                            "energije. Na Europi su uočeni gejziri leda koji eruptiraju iz oceana, što dodatno sugerira prisutnost tekuće vode. S obzirom na " +
                            "njezinu veličinu, Europa je najzanimljiviji mjesec Jupitera i često je predmet istraživanja za misije koje traže potencijalne " +
                            "uvjete za život.",
                    velicina = 3121.0,
                    zanimljivosti = "Europa je jedno od najzanimljivijih mjesta za buduća istraživanja, jer znanstvenici vjeruju da bi pod njenom ledenom korom mogao " +
                            "postojati život. Misija NASA-e \"Europa Clipper\" ima za cilj detaljno istražiti ovaj mjesec."
                ),
                MjesecInfo(
                    ime = "Titan",
                    planetIme = "Saturn",
                    kratkiOpis = "Titan je najveći mjesec Saturna i drugi po veličini u Sunčevom sustavu. Ima atmosferu koja je vrlo gusta i bogata metanom.",
                    detaljanOpis = "Titan je jedini mjesec u Sunčevom sustavu koji ima gustu atmosferu, koja je uglavnom sastavljena od dušika i metana. Zbog toga, " +
                            "na Titanovoj površini padaju metanske kiše, a tekućina na njegovoj površini nije voda, već metan i etan. Titan također ima tekuće " +
                            "jezera i mora, koja su formirana od tekućih ugljikovodika. Pod njegovom površinom se smatra da bi mogao postojati ocean koji je " +
                            "vrući i vodenasti. Titanov interes za znanstvenike leži u njegovoj atmosferi i mogućnosti razvoja prebiotskih kemijskih procesa.",
                    velicina = 5151.0,
                    zanimljivosti = "Titan je jedini mjesec koji ima gustu atmosferu i možda je najviše sliči ranim uvjetima na Zemlji. NASA-ina misija \"Cassini-Huygens\" " +
                            "iz 2005. godine omogućila je spuštanje na Titan i prva je napravila analize njegove površine."
                ),
                MjesecInfo(
                    ime = "Gaimed",
                    planetIme = "Jupiter",
                    kratkiOpis = "Ganimed je najveći mjesec u Sunčevom sustavu i jedini mjesec koji ima magnetsko polje.",
                    detaljanOpis = "Ganimed je veći od Merkura i jedini mjesec u Sunčevom sustavu koji ima vlastito magnetsko polje. Njegova površina je prekrivena slojem " +
                            "leda, a ispod nje se nalazi vjerojatno tekući ocean. Ganimed također ima atmosferu koja je vrlo tanka i sastoji se od kisika, ali je " +
                            "suviše tanka da bi podržala ljudski život. Svi podaci upućuju na to da bi Ganimed mogao imati uvjete pogodne za razvoj života pod " +
                            "njegovom površinom, zbog geotermalne energije koja može održavati tekuću vodu.",
                    velicina = 5268.0,
                    zanimljivosti = "Ganimed je jedini mjesec u Sunčevom sustavu koji ima magnetsko polje, što ga čini jedinstvenim među mjesecima. Misije poput \"JUICE\" " +
                            "(JUpiter ICy moons Explorer) planiraju detaljnije istražiti Ganimed u budućnosti."
                ),
                MjesecInfo(
                    ime = "Io",
                    planetIme = "Jupiter",
                    kratkiOpis = "Io je mjesec Jupitera, poznat po svojoj vulkanskoj aktivnosti. Ima najveći broj vulkana u Sunčevom sustavu.",
                    detaljanOpis = "Io je jedan od najaktivnijih svjetlosnih tijela u Sunčevom sustavu, zahvaljujući svojoj snažnoj vulkanskoj aktivnosti. Iako je ledena, " +
                            "površina Ioa prekrivena je lavama, dok vulkani eruptiraju u veliku visinu. Zbog plimskih sila koje Io doživljava od strane Jupitera i " +
                            "njegovih drugih mjeseca (Europa i Ganimed), planeti uzrokuju trenje u njegovoj unutrašnjosti, što generira toplinu koja uzrokuje vulkane. " +
                            "Io je neobičan jer nema nijednu vodu, a atmosfera mu je izuzetno tanka.",
                    velicina = 3643.0,
                    zanimljivosti = "Io je planet s najviše vulkanske aktivnosti u Sunčevom sustavu, što znači da je geološki najaktivniji mjesec. U njegovoj atmosferi nalazi " +
                            "se uglavnom sumporni dioksid."
                ),
                MjesecInfo(
                    ime = "Triton",
                    planetIme = "Neptun",
                    kratkiOpis = "Triton je najveći mjesec Neptuna i jedini veliki mjesec koji ima retrogradnu orbitu.",
                    detaljanOpis = "Triton je poznat po svojoj retrogradnoj orbiti, što znači da orbitira u suprotnom smjeru od većine drugih mjeseca u Sunčevom sustavu. Na " +
                            "Tritonu se nalaze gejziri koji izbacuju tekući dušik u svemir, zbog čega je ovo jedno od najzanimljivijih tijela u Sunčevom sustavu. " +
                            "Triton ima vrlo hladnu površinu, koja se sastoji od leda i stijena, a njegovi gejziri ukazuju na unutrašnju toplinu, što sugerira da bi " +
                            "mogao imati podzemni ocean.",
                    velicina = 2710.0,
                    zanimljivosti = "Triton je jedini veliki mjesec u Sunčevom sustavu koji ima retrogradnu orbitu, a to znači da je vjerojatno bio uhvaćen od strane Neptuna."
                ),
                MjesecInfo(
                    ime = "Miranda",
                    planetIme = "Uran",
                    kratkiOpis = "Miranda je jedan od mjeseca Urana, poznat po svojoj neobičnoj geološkoj povijesti.",
                    detaljanOpis = "Miranda je mjesec Urana koji je zanimljiv zbog svojih velikih geoloških razlika. Površina Mirande prekrivena je visokim planinama, dubokim " +
                            "kanjonima i velikim udubinama. Znanstvenici vjeruju da je Miranda doživjela masivnu sudar koja je stvorila mnoge od ovih značajki. Njezin " +
                            "geološki 'kaotičan' izgled ukazuje na to da je mjesec prošao kroz različite faze evolucije, uključujući preoblikovanje koje se dogodilo " +
                            "prije milijarde godina.",
                    velicina = 471.6,
                    zanimljivosti = "Miranda ima jedne od najnevjerojatnijih geoloških značajki u Sunčevom sustavu, uključujući kanjone duboke do 20 km."
                )

            )

            val moonsToInsert = defaultMoons.filter {
                it.ime.lowercase() !in existingNames &&
                        planetNameToId.containsKey(it.planetIme.lowercase())
            }.map {
                Mjesec(
                    ime = it.ime,
                    kratkiOpis = it.kratkiOpis,
                    detaljanOpis = it.detaljanOpis,
                    velicina = it.velicina,
                    zanimljivosti = it.zanimljivosti,
                    planetId = planetNameToId[it.planetIme.lowercase()]!!.id
                )
            }

            if (moonsToInsert.isNotEmpty()) {
                for (moon in moonsToInsert) {
                    dao.insertMjesec(moon)
                }
            }
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
