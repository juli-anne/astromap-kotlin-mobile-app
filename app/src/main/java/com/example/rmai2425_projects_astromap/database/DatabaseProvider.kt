package com.example.rmai2425_projects_astromap.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AstroMapDatabase? = null

    fun getDatabase(context: Context): AstroMapDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AstroMapDatabase::class.java,
                "astro_map_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
