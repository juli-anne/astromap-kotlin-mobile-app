package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Komet

@Dao
interface KometDao {
    @Insert
    suspend fun insert(komet: Komet)

    @Query("SELECT * FROM kometi WHERE id = :kometId")
    suspend fun getById(kometId: Int): Komet?

    @Query("SELECT * FROM kometi")
    suspend fun getAll(): List<Komet>

    @Delete
    suspend fun delete(komet: Komet)
}