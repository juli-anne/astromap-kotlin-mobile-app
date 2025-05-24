package com.example.rmai2425_projects_astromap.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseInitializer {

    suspend fun initDatabase(dao: EntitiesDao) {
        withContext(Dispatchers.IO) {

            val planetIds = mutableMapOf<String, Int>()
            MockDataLoader.getPlanets().forEach { planet ->
                val id = dao.insertPlanet(planet).toInt()
                planetIds[planet.ime] = id
            }

            MockDataLoader.getMoonsInfo().forEach { mjesecInfo ->
                val planetId = planetIds[mjesecInfo.planetIme]
                if (planetId == null) {
                    Log.e("DatabaseInitializer", "Planet nije pronađen za mjesec ${mjesecInfo.ime}")
                    return@forEach
                }
                val mjesec = Mjesec(
                    ime = mjesecInfo.ime,
                    planetId = planetId,
                    kratkiOpis = mjesecInfo.kratkiOpis,
                    detaljanOpis = mjesecInfo.detaljanOpis,
                    velicina = mjesecInfo.velicina,
                    zanimljivosti = mjesecInfo.zanimljivosti
                )
                dao.insertMjesec(mjesec)
            }

            MockDataLoader.getSunce().forEach { dao.insertSunce(it) }
            MockDataLoader.getAsteroids().forEach { dao.insertAsteroid(it) }
            MockDataLoader.getComets().forEach { dao.insertKomet(it) }
            MockDataLoader.getObjects().forEach { dao.insertObjekt(it) }
            MockDataLoader.getZvijezdja().forEach { dao.insertZvijezdje(it) }

            val planetiPitanja = MockDataLoader.getKvizPitanjaOPlanetima()
            dao.insertKvizPitanja(planetiPitanja)

            val suncePitanja = MockDataLoader.getKvizPitanjaOSuncu()
            dao.insertKvizPitanja(suncePitanja)

            val mjeseciPitanja = MockDataLoader.getKvizPitanjaOMjesecima()
            dao.insertKvizPitanja(mjeseciPitanja)

            val asteroidiPitanja = MockDataLoader.getKvizPitanjaOAsteroidima()
            dao.insertKvizPitanja(asteroidiPitanja)

            val kometiPitanja = MockDataLoader.getKvizPitanjaOKometima()
            dao.insertKvizPitanja(kometiPitanja)

            val objektiPitanja = MockDataLoader.getKvizPitanjaOObjektima()
            dao.insertKvizPitanja(objektiPitanja)

            val zvijezdjaPitanja = MockDataLoader.getKvizPitanjaOZvijezdjima()
            dao.insertKvizPitanja(zvijezdjaPitanja)

            initSunSystemInfo(dao)
        }
    }

    private suspend fun initSunSystemInfo(dao: EntitiesDao) {
        val existingInfo = dao.getAllSunSystemInfo()
        if (existingInfo.isEmpty()) {
            val defaultInfo = MockDataLoader.getSunSystemInfo().map { mockInfo ->
                SuncevSustavInfo(
                    naslov = mockInfo.naslov,
                    kratkiOpis = mockInfo.kratkiOpis,
                    detaljanOpis = mockInfo.detaljanOpis,
                    kategorija = mockInfo.kategorija
                )
            }
            dao.insertSunSystemInfos(defaultInfo)
        }
    }
}