package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Planet

@Dao
interface PlanetDao {
    @Insert
    suspend fun insert(planet: Planet): Long

    @Insert
    suspend fun insertAll(planets: List<Planet>): List<Long>

    @Query("SELECT * FROM planeti WHERE id = :planetId")
    suspend fun getById(planetId: Int): Planet?

    @Query("SELECT * FROM planeti")
    suspend fun getAll(): List<Planet>

    @Delete
    suspend fun delete(planet: Planet)
}