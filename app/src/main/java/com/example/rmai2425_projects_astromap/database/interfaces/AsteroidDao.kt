package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Asteroid

@Dao
interface AsteroidDao {
    @Insert
    suspend fun insert(asteroid: Asteroid)

    @Query("SELECT * FROM asteroidi WHERE id = :asteroidId")
    suspend fun getById(asteroidId: Int): Asteroid?

    @Query("SELECT * FROM asteroidi")
    suspend fun getAll(): List<Asteroid>

    @Delete
    suspend fun delete(asteroid: Asteroid)
}