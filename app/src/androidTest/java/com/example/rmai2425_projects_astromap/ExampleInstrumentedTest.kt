package com.example.rmai2425_projects_astromap

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rmai2425_projects_astromap.database.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: AstroMapDatabase
    private lateinit var dao: EntitiesDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AstroMapDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.entitiesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testDatabaseInitialization() = runBlocking {
        DatabaseInitializer.initDatabase(dao)
        val planets = dao.getAllPlanets()
        val moons = dao.getAllMjeseci()
        val sunce = dao.getAllSunca()
        val asteroids = dao.getAllAsteroidi()
        val comets = dao.getAllKometi()
        val objects = dao.getAllObjekti()
        val constellations = dao.getAllZvijezdja()
        assertTrue(planets.isNotEmpty())
        assertTrue(moons.isNotEmpty())
        assertTrue(sunce.isNotEmpty())
        assertTrue(asteroids.isNotEmpty())
        assertTrue(comets.isNotEmpty())
        assertTrue(objects.isNotEmpty())
        assertTrue(constellations.isNotEmpty())
    }
}
