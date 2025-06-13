package com.example.rmai2425_projects_astromap.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Planet::class,
        Mjesec::class,
        Sunce::class,
        Asteroid::class,
        Komet::class,
        ObjektSuncevogSustava::class,
        VezaIzmeduPlanetaMjeseca::class,
        Zvijezdje::class,
        KvizPitanje::class,
        SuncevSustavInfo::class,
        Korisnik::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AstroMapDatabase : RoomDatabase() {
    abstract fun entitiesDao(): EntitiesDao
}
