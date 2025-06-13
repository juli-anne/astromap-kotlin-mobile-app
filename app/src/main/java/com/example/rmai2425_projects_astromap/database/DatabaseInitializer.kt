package com.example.rmai2425_projects_astromap.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseInitializer {
    suspend fun initDatabase(database: AstroMapDatabase) {
        withContext(Dispatchers.IO) {
            val planetIds = mutableMapOf<String, Int>()

            MockDataLoader.getPlanets().forEach { planet ->
                val id = database.planetDao().insert(planet).toInt()
                planetIds[planet.ime] = id
            }

            MockDataLoader.getMoonsInfo().forEach { mjesecInfo ->
                val planetId = planetIds[mjesecInfo.planetIme]
                if (planetId == null) {
                    Log.e("DatabaseInitializer", "Planet nije pronađen za mjesec: ${mjesecInfo.ime}")
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
                database.mjesecDao().insert(mjesec)
            }

            MockDataLoader.getSunce().forEach { database.sunceDao().insert(it) }
            MockDataLoader.getAsteroids().forEach { database.asteroidDao().insert(it) }
            MockDataLoader.getComets().forEach { database.kometDao().insert(it) }
            MockDataLoader.getObjects().forEach { database.objektSuncevogSustavaDao().insert(it) }
            MockDataLoader.getZvijezdja().forEach { database.zvijezdjeDao().insert(it) }

            val planetiPitanja = MockDataLoader.getKvizPitanjaOPlanetima()
            database.kvizPitanjeDao().insertAll(planetiPitanja)

            val suncePitanja = MockDataLoader.getKvizPitanjaOSuncu()
            database.kvizPitanjeDao().insertAll(suncePitanja)

            val mjeseciPitanja = MockDataLoader.getKvizPitanjaOMjesecima()
            database.kvizPitanjeDao().insertAll(mjeseciPitanja)

            val asteroidiPitanja = MockDataLoader.getKvizPitanjaOAsteroidima()
            database.kvizPitanjeDao().insertAll(asteroidiPitanja)

            val kometiPitanja = MockDataLoader.getKvizPitanjaOKometima()
            database.kvizPitanjeDao().insertAll(kometiPitanja)

            val objektiPitanja = MockDataLoader.getKvizPitanjaOObjektima()
            database.kvizPitanjeDao().insertAll(objektiPitanja)

            val zvijezdjaPitanja = MockDataLoader.getKvizPitanjaOZvijezdjima()
            database.kvizPitanjeDao().insertAll(zvijezdjaPitanja)

            initSunSystemInfo(database)
        }
    }

    private suspend fun initSunSystemInfo(database: AstroMapDatabase) {
        val existingInfo = database.suncevSustavDao().getAll()
        if (existingInfo.isEmpty()) {
            val defaultInfo = MockDataLoader.getSunSystemInfo().map { mockInfo ->
                SuncevSustavInfo(
                    naslov = mockInfo.naslov,
                    kratkiOpis = mockInfo.kratkiOpis,
                    detaljanOpis = mockInfo.detaljanOpis,
                    kategorija = mockInfo.kategorija
                )
            }
            database.suncevSustavDao().insertAll(defaultInfo)
        }
    }
}