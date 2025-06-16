package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Zvijezdje

@Dao
interface ZvijezdjeDao {
    @Insert
    suspend fun insert(zvijezdje: Zvijezdje)

    @Query("SELECT * FROM zvijezdja WHERE id = :zvijezdjeId")
    suspend fun getById(zvijezdjeId: Int): Zvijezdje?

    @Query("SELECT * FROM zvijezdja")
    suspend fun getAll(): List<Zvijezdje>

    @Delete
    suspend fun delete(zvijezdje: Zvijezdje)
}