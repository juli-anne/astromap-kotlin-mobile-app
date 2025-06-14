package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.VezaIzmeduPlanetaMjeseca

@Dao
interface VezaIzmeduPlanetaMjesecaDao {
    @Insert
    suspend fun insert(veza: VezaIzmeduPlanetaMjeseca)

    @Query("SELECT * FROM planeti_mjeseci WHERE id = :vezaId")
    suspend fun getById(vezaId: Int): VezaIzmeduPlanetaMjeseca?

    @Query("SELECT * FROM planeti_mjeseci")
    suspend fun getAll(): List<VezaIzmeduPlanetaMjeseca>

    @Delete
    suspend fun delete(veza: VezaIzmeduPlanetaMjeseca)
}