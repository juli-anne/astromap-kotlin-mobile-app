package com.example.rmai2425_projects_astromap.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao
interface EntitiesDao {

    // Planeti
    @Insert
    suspend fun insertPlanet(planet: Planet): Long

    @Insert
    suspend fun insertPlanets(planets: List<Planet>): List<Long>

    @Query("SELECT * FROM planeti WHERE id = :planetId")
    suspend fun getPlanetById(planetId: Int): Planet?

    @Query("SELECT * FROM planeti")
    suspend fun getAllPlanets(): List<Planet>

    @Delete
    suspend fun deletePlanet(planet: Planet)

    // Mjeseci
    @Insert
    suspend fun insertMjesec(mjesec: Mjesec)

    @Query("SELECT * FROM mjeseci WHERE id = :mjesecId")
    suspend fun getMjesecById(mjesecId: Int): Mjesec?

    @Query("SELECT * FROM mjeseci")
    suspend fun getAllMjeseci(): List<Mjesec>

    @Delete
    suspend fun deleteMjesec(mjesec: Mjesec)

    // Sunca
    @Insert
    suspend fun insertSunce(sunce: Sunce)

    @Query("SELECT * FROM sunca WHERE id = :sunceId")
    suspend fun getSunceById(sunceId: Int): Sunce?

    @Query("SELECT * FROM sunca")
    suspend fun getAllSunca(): List<Sunce>

    @Delete
    suspend fun deleteSunce(sunce: Sunce)

    // Asteroidi
    @Insert
    suspend fun insertAsteroid(asteroid: Asteroid)

    @Query("SELECT * FROM asteroidi WHERE id = :asteroidId")
    suspend fun getAsteroidById(asteroidId: Int): Asteroid?

    @Query("SELECT * FROM asteroidi")
    suspend fun getAllAsteroidi(): List<Asteroid>

    @Delete
    suspend fun deleteAsteroid(asteroid: Asteroid)

    // Kometi
    @Insert
    suspend fun insertKomet(komet: Komet)

    @Query("SELECT * FROM kometi WHERE id = :kometId")
    suspend fun getKometById(kometId: Int): Komet?

    @Query("SELECT * FROM kometi")
    suspend fun getAllKometi(): List<Komet>

    @Delete
    suspend fun deleteKomet(komet: Komet)

    // Objekti Sunčevog sustava
    @Insert
    suspend fun insertObjekt(objekt: ObjektSuncevogSustava)

    @Query("SELECT * FROM objekti WHERE id = :objektId")
    suspend fun getObjektById(objektId: Int): ObjektSuncevogSustava?

    @Query("SELECT * FROM objekti")
    suspend fun getAllObjekti(): List<ObjektSuncevogSustava>

    @Delete
    suspend fun deleteObjekt(objekt: ObjektSuncevogSustava)

    // Veze Planeti - Mjeseci
    @Insert
    suspend fun insertVeza(veza: VezaIzmeduPlanetaMjeseca)

    @Query("SELECT * FROM planeti_mjeseci WHERE id = :vezaId")
    suspend fun getVezaById(vezaId: Int): VezaIzmeduPlanetaMjeseca?

    @Query("SELECT * FROM planeti_mjeseci")
    suspend fun getAllVeze(): List<VezaIzmeduPlanetaMjeseca>

    @Delete
    suspend fun deleteVeza(veza: VezaIzmeduPlanetaMjeseca)

    // Zviježđa
    @Insert
    suspend fun insertZvijezdje(zvijezdje: Zvijezdje)

    @Query("SELECT * FROM zvijezdja WHERE id = :zvijezdjeId")
    suspend fun getZvijezdjeById(zvijezdjeId: Int): Zvijezdje?

    @Query("SELECT * FROM zvijezdja")
    suspend fun getAllZvijezdja(): List<Zvijezdje>

    @Delete
    suspend fun deleteZvijezdje(zvijezdje: Zvijezdje)
}
