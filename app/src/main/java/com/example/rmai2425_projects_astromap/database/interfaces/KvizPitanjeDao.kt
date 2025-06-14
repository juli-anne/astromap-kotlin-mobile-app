package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.KvizPitanje

@Dao
interface KvizPitanjeDao {
    @Insert
    suspend fun insert(kvizPitanje: KvizPitanje)

    @Insert
    suspend fun insertAll(kvizPitanja: List<KvizPitanje>): List<Long>

    @Query("SELECT * FROM kviz_pitanja WHERE id = :pitanjeId")
    suspend fun getById(pitanjeId: Int): KvizPitanje?

    @Query("SELECT * FROM kviz_pitanja WHERE kategorija = :kategorija")
    suspend fun getByKategorija(kategorija: String): List<KvizPitanje>

    @Query("SELECT * FROM kviz_pitanja")
    suspend fun getAll(): List<KvizPitanje>

    @Delete
    suspend fun delete(kvizPitanje: KvizPitanje)
}