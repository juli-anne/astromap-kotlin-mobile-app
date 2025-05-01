package com.example.rmai2425_projects_astromap.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseInitializer {
    suspend fun initDatabase(dao: EntitiesDao) = withContext(Dispatchers.IO) {

        val planetIds = mutableMapOf<String, Int>()
        MockDataLoader.getPlanets().forEach { planet ->
            val id = dao.insertPlanet(planet).toInt()
            planetIds[planet.ime] = id
        }

        MockDataLoader.getMoonsInfo().forEach { mjesecInfo ->
            val planetId = planetIds[mjesecInfo.planetIme] ?: return@forEach
            val mjesec = Mjesec(
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
    }
}
