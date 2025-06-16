package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Mjesec

@Dao
interface MjesecDao {
    @Insert
    suspend fun insert(mjesec: Mjesec)

    @Query("SELECT * FROM mjeseci WHERE id = :mjesecId")
    suspend fun getById(mjesecId: Int): Mjesec?

    @Query("SELECT * FROM mjeseci")
    suspend fun getAll(): List<Mjesec>

    @Delete
    suspend fun delete(mjesec: Mjesec)
}