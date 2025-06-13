package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rmai2425_projects_astromap.database.DovrseniModul

@Dao
interface DovrseniModulDao {
    @Insert
    suspend fun insert(modul: DovrseniModul)

    @Query("SELECT * FROM dovrseni_moduli WHERE userId = :userId")
    suspend fun getByUserId(userId: Int): List<DovrseniModul>
}