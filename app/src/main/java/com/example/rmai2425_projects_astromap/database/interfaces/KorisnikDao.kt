package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.Korisnik

@Dao
interface KorisnikDao {
    @Insert
    suspend fun insert(korisnik: Korisnik): Long

    @Query("SELECT * FROM korisnici WHERE email = :email AND password = :password")
    suspend fun getByEmailAndPassword(email: String, password: String): Korisnik?

    @Query("SELECT * FROM korisnici WHERE email = :email")
    suspend fun getByEmail(email: String): Korisnik?

    @Query("SELECT * FROM korisnici WHERE id = :userId")
    suspend fun getById(userId: Int): Korisnik?

    @Query("SELECT * FROM korisnici")
    suspend fun getAll(): List<Korisnik>

    @Delete
    suspend fun delete(korisnik: Korisnik)
}