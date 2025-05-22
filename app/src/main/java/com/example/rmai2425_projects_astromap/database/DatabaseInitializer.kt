package com.example.rmai2425_projects_astromap.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseInitializer {

    suspend fun initDatabase(dao: EntitiesDao) {
        withContext(Dispatchers.IO) {
            // Inicijalizacija planeta
            val planetIds = mutableMapOf<String, Int>()
            MockDataLoader.getPlanets().forEach { planet ->
                val id = dao.insertPlanet(planet).toInt()
                planetIds[planet.ime] = id
            }

            // Inicijalizacija mjeseca
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

            // Inicijalizacija Sunca
            MockDataLoader.getSunce().forEach { dao.insertSunce(it) }

            // Inicijalizacija asteroida
            MockDataLoader.getAsteroids().forEach { dao.insertAsteroid(it) }

            // Inicijalizacija kometa
            MockDataLoader.getComets().forEach { dao.insertKomet(it) }

            // Inicijalizacija objekata
            MockDataLoader.getObjects().forEach { dao.insertObjekt(it) }

            // Inicijalizacija zvijezđa
            MockDataLoader.getZvijezdja().forEach { dao.insertZvijezdje(it) }

            // Inicijalizacija podataka o Sunčevom sustavu
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
