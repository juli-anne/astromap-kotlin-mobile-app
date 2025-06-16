package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Sunce

@Dao
interface SunceDao {
    @Insert
    suspend fun insert(sunce: Sunce)

    @Query("SELECT * FROM sunca WHERE id = :sunceId")
    suspend fun getById(sunceId: Int): Sunce?

    @Query("SELECT * FROM sunca")
    suspend fun getAll(): List<Sunce>

    @Delete
    suspend fun delete(sunce: Sunce)
}