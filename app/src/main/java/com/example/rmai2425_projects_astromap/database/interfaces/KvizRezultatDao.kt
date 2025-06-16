package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rmai2425_projects_astromap.database.KvizRezultat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Dao
interface KvizRezultatDao {
    @Insert
    suspend fun insert(rezultat: KvizRezultat)

    @Query("SELECT * FROM kviz_rezultati WHERE userId = :userId")
    suspend fun getByUserId(userId: Int): List<KvizRezultat>

    @Query("UPDATE kviz_rezultati SET najboljiRezultat = :noviRezultat, datum = :datum WHERE userId = :userId AND kvizId = :kvizId")
    suspend fun updateRezultat(
        userId: Int,
        kvizId: String,
        noviRezultat: Int,
        datum: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    )
}