package com.example.rmai2425_projects_astromap.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rmai2425_projects_astromap.database.interfaces.MjesecDao
import com.example.rmai2425_projects_astromap.database.interfaces.PlanetDao
import com.example.rmai2425_projects_astromap.database.interfaces.SuncevSustavDao
import com.example.rmai2425_projects_astromap.database.interfaces.AsteroidDao
import com.example.rmai2425_projects_astromap.database.interfaces.DovrseniModulDao
import com.example.rmai2425_projects_astromap.database.interfaces.HighScoreDao
import com.example.rmai2425_projects_astromap.database.interfaces.KometDao
import com.example.rmai2425_projects_astromap.database.interfaces.KorisnikDao
import com.example.rmai2425_projects_astromap.database.interfaces.KvizPitanjeDao
import com.example.rmai2425_projects_astromap.database.interfaces.KvizRezultatDao
import com.example.rmai2425_projects_astromap.database.interfaces.ObjektSuncevogSustavaDao
import com.example.rmai2425_projects_astromap.database.interfaces.SunceDao
import com.example.rmai2425_projects_astromap.database.interfaces.VezaIzmeduPlanetaMjesecaDao
import com.example.rmai2425_projects_astromap.database.interfaces.ZvijezdjeDao

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
        Korisnik::class,
        DovrseniModul::class,
        KvizRezultat::class,
        HighScore::class
    ],
    version = 7,
    exportSchema = false
)
abstract class AstroMapDatabase : RoomDatabase() {
    abstract fun suncevSustavDao(): SuncevSustavDao
    abstract fun planetDao(): PlanetDao
    abstract fun mjesecDao(): MjesecDao
    abstract fun sunceDao(): SunceDao
    abstract fun asteroidDao(): AsteroidDao
    abstract fun kometDao(): KometDao
    abstract fun objektSuncevogSustavaDao(): ObjektSuncevogSustavaDao
    abstract fun vezaDao(): VezaIzmeduPlanetaMjesecaDao
    abstract fun zvijezdjeDao(): ZvijezdjeDao
    abstract fun kvizPitanjeDao(): KvizPitanjeDao
    abstract fun korisnikDao(): KorisnikDao
    abstract fun dovrseniModulDao(): DovrseniModulDao
    abstract fun kvizRezultatDao(): KvizRezultatDao
    abstract fun highScoreDao(): HighScoreDao
}