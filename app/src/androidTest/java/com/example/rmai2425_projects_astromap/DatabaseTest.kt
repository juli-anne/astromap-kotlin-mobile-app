package com.example.rmai2425_projects_astromap

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rmai2425_projects_astromap.database.*
import com.example.rmai2425_projects_astromap.database.interfaces.AsteroidDao
import com.example.rmai2425_projects_astromap.database.interfaces.DovrseniModulDao
import com.example.rmai2425_projects_astromap.database.interfaces.KometDao
import com.example.rmai2425_projects_astromap.database.interfaces.KorisnikDao
import com.example.rmai2425_projects_astromap.database.interfaces.KvizPitanjeDao
import com.example.rmai2425_projects_astromap.database.interfaces.KvizRezultatDao
import com.example.rmai2425_projects_astromap.database.interfaces.MjesecDao
import com.example.rmai2425_projects_astromap.database.interfaces.ObjektSuncevogSustavaDao
import com.example.rmai2425_projects_astromap.database.interfaces.PlanetDao
import com.example.rmai2425_projects_astromap.database.interfaces.SunceDao
import com.example.rmai2425_projects_astromap.database.interfaces.ZvijezdjeDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: AstroMapDatabase
    private lateinit var planetDao: PlanetDao
    private lateinit var mjesecDao: MjesecDao
    private lateinit var sunceDao: SunceDao
    private lateinit var asteroidDao: AsteroidDao
    private lateinit var kometDao: KometDao
    private lateinit var objektDao: ObjektSuncevogSustavaDao
    private lateinit var zvijezdjeDao: ZvijezdjeDao
    private lateinit var kvizPitanjeDao: KvizPitanjeDao
    private lateinit var korisnikDao: KorisnikDao
    private lateinit var kvizRezultatDao: KvizRezultatDao
    private lateinit var dovrseniModulDao: DovrseniModulDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AstroMapDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        planetDao = db.planetDao()
        mjesecDao = db.mjesecDao()
        sunceDao = db.sunceDao()
        asteroidDao = db.asteroidDao()
        kometDao = db.kometDao()
        objektDao = db.objektSuncevogSustavaDao()
        zvijezdjeDao = db.zvijezdjeDao()
        kvizPitanjeDao = db.kvizPitanjeDao()
        korisnikDao = db.korisnikDao()
        kvizRezultatDao = db.kvizRezultatDao()
        dovrseniModulDao = db.dovrseniModulDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    /**
     * test osnovnih CRUD operacija za planete
     * provjerava unos, čitanje i brisanje planeta iz baze
     */
    @Test
    fun testPlanetCrudOperations() = runBlocking {
        val testPlanet = Planet(
            ime = "Test Planet",
            kratkiOpis = "Test opis",
            detaljanOpis = "Detaljan test opis",
            povrsinskaTemperaturaDan = "100C",
            povrsinskaTemperaturaNoc = "-100C",
            promjer = 5000.0,
            imaMjesec = true
        )

        // insert
        val insertedId = planetDao.insert(testPlanet)
        assertTrue(insertedId > 0)

        // read
        val retrievedPlanet = planetDao.getById(insertedId.toInt())
        assertNotNull(retrievedPlanet)
        assertEquals("Test Planet", retrievedPlanet?.ime)

        // delete
        if (retrievedPlanet != null) {
            planetDao.delete(retrievedPlanet)
            val deletedPlanet = planetDao.getById(insertedId.toInt())
            assertNull(deletedPlanet)
        }
    }

    /**
     * test relacija između planeta i mjeseca
     * šrovjerava da svaki mjesec u bazi ima valjan planetId koji postoji u tablici planeta
     */
    @Test
    fun testPlanetMoonRelationship() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val planeti = planetDao.getAll()
        val mjeseci = mjesecDao.getAll()

        assertTrue(planeti.isNotEmpty())
        assertTrue(mjeseci.isNotEmpty())

        mjeseci.forEach { mjesec ->
            val planet = planeti.find { it.id == mjesec.planetId }
            assertNotNull("Mjesec ${mjesec.ime} nema valjan planetId", planet)
        }
    }

    /**
     * rest osnovne kviz funkcionalnosti
     * provjerava da postoje pitanja za glavne kategorije i da imaju ispravnu strukturu
     */
    @Test
    fun testQuizFunctionality() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val pitanjaOPlanetima = kvizPitanjeDao.getByKategorija("Planeti")
        val pitanjaOSuncu = kvizPitanjeDao.getByKategorija("Sunce")

        assertTrue("Nema pitanja o planetima", pitanjaOPlanetima.isNotEmpty())
        assertTrue("Nema pitanja o suncu", pitanjaOSuncu.isNotEmpty())

        pitanjaOPlanetima.forEach { pitanje ->
            assertFalse("Pitanje je prazno", pitanje.pitanje.isBlank())
            assertFalse("Točan odgovor je prazan", pitanje.tocniOdgovor.isBlank())
            assertFalse("Netočan odgovor 1 je prazan", pitanje.netocniOdgovor1.isBlank())
        }
    }

    /**
     * test korisničkih funkcionalnosti
     * provjerava registraciju korisnika i dohvaćanje korisničkih podataka
     */
    @Test
    fun testUserManagement() = runBlocking {
        val testKorisnik = Korisnik(
            ime = "Test User",
            email = "test@example.com",
            password = "hashedPassword123"
        )

        val userId = korisnikDao.insert(testKorisnik)
        assertTrue(userId > 0)

        val retrievedUser = korisnikDao.getById(userId.toInt())
        assertNotNull(retrievedUser)
        assertEquals("test@example.com", retrievedUser?.email)
    }

    /**
     * test kviz rezultata
     * provjerava spremanje i dohvaćanje kviz rezultata za korisnika
     */
    @Test
    fun testQuizResults() = runBlocking {
        val testKorisnik = Korisnik(
            ime = "Quiz Tester",
            email = "quiz@test.com",
            password = "password"
        )
        val userId = korisnikDao.insert(testKorisnik).toInt()

        val kvizRezultat = KvizRezultat(
            userId = userId,
            kvizId = "planeti_kviz",
            najboljiRezultat = 85
        )

        kvizRezultatDao.insert(kvizRezultat)

        val rezultati = kvizRezultatDao.getByUserId(userId)
        assertTrue(rezultati.isNotEmpty())
        assertEquals(85, rezultati.first().najboljiRezultat)
    }

    /**
     * test dovršenih modula
     * provjerava označavanje modula kao dovršenih za korisnika
     */
    @Test
    fun testCompletedModules() = runBlocking {
        val testKorisnik = Korisnik(
            ime = "Module Tester",
            email = "module@test.com",
            password = "password"
        )
        val userId = korisnikDao.insert(testKorisnik).toInt()

        val dovrseniModul = DovrseniModul(
            userId = userId,
            modulId = "planeti_modul"
        )

        dovrseniModulDao.insert(dovrseniModul)

        val moduli = dovrseniModulDao.getByUserId(userId)
        assertTrue(moduli.isNotEmpty())
        assertEquals("planeti_modul", moduli.first().modulId)
    }

    /**
     * test performansi baze podataka
     * provjerava da inicijalizacija i osnovni upiti rade u razumnom vremenu
     */
    @Test
    fun testDatabasePerformance() = runBlocking {
        val startTime = System.currentTimeMillis()

        DatabaseInitializer.initDatabase(db)

        val endTime = System.currentTimeMillis()
        val initTime = endTime - startTime

        assertTrue("Inicijalizacija baze traje predugo: ${initTime}ms", initTime < 5000)

        val queryStartTime = System.currentTimeMillis()
        val sviPlaneti = planetDao.getAll()
        val queryEndTime = System.currentTimeMillis()
        val queryTime = queryEndTime - queryStartTime

        assertTrue("Query traje predugo: ${queryTime}ms", queryTime < 1000)
        assertTrue("Nema podataka", sviPlaneti.isNotEmpty())
    }

    /**
     * test integriteta podataka
     * provjerava da svi podaci u bazi imaju potrebne vrijednosti i da nema praznih polja
     */
    @Test
    fun testDataIntegrity() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val planeti = planetDao.getAll()
        planeti.forEach { planet ->
            assertFalse("Planet ${planet.ime} nema ime", planet.ime.isBlank())
            assertFalse("Planet ${planet.ime} nema kratak opis", planet.kratkiOpis.isBlank())
            assertTrue("Planet ${planet.ime} ima negativan promjer", planet.promjer > 0)
        }

        val svaPitanja = kvizPitanjeDao.getAll()
        svaPitanja.forEach { pitanje ->
            assertFalse("Pitanje ID ${pitanje.id} nema tekst", pitanje.pitanje.isBlank())
            assertFalse("Pitanje ID ${pitanje.id} nema točan odgovor", pitanje.tocniOdgovor.isBlank())
            assertFalse("Pitanje ID ${pitanje.id} nema prvi netočan odgovor", pitanje.netocniOdgovor1.isBlank())
            assertFalse("Pitanje ID ${pitanje.id} nema drugi netočan odgovor", pitanje.netocniOdgovor2.isBlank())
            assertFalse("Pitanje ID ${pitanje.id} nema treći netočan odgovor", pitanje.netocniOdgovor3.isBlank())
        }
    }
}