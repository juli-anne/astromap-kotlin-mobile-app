package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.rmai2425_projects_astromap.database.ObjektSuncevogSustava

@Dao
interface ObjektSuncevogSustavaDao {
    @Insert
    suspend fun insert(objekt: ObjektSuncevogSustava)

    @Query("SELECT * FROM objekti WHERE id = :objektId")
    suspend fun getById(objektId: Int): ObjektSuncevogSustava?

    @Query("SELECT * FROM objekti")
    suspend fun getAll(): List<ObjektSuncevogSustava>

    @Delete
    suspend fun delete(objekt: ObjektSuncevogSustava)
}