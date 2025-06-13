package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.SuncevSustavInfo

@Dao
interface SuncevSustavDao {
    @Insert
    suspend fun insert(info: SuncevSustavInfo)

    @Insert
    suspend fun insertAll(infos: List<SuncevSustavInfo>): List<Long>

    @Query("SELECT * FROM suncev_sustav WHERE id = :infoId")
    suspend fun getById(infoId: Int): SuncevSustavInfo?

    @Query("SELECT * FROM suncev_sustav")
    suspend fun getAll(): List<SuncevSustavInfo>

    @Query("SELECT * FROM suncev_sustav WHERE kategorija = :kategorija")
    suspend fun getByCategory(kategorija: String): List<SuncevSustavInfo>

    @Delete
    suspend fun delete(info: SuncevSustavInfo)
}