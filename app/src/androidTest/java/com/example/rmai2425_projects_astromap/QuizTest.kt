package com.example.rmai2425_projects_astromap

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rmai2425_projects_astromap.database.*
import com.example.rmai2425_projects_astromap.database.interfaces.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuizTest {

    private lateinit var db: AstroMapDatabase
    private lateinit var kvizPitanjeDao: KvizPitanjeDao
    private lateinit var kvizRezultatDao: KvizRezultatDao
    private lateinit var korisnikDao: KorisnikDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AstroMapDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        kvizPitanjeDao = db.kvizPitanjeDao()
        kvizRezultatDao = db.kvizRezultatDao()
        korisnikDao = db.korisnikDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    /**
     * test provjerava imaju li sve kategorije kviza pitanja u bazi
     * i ima li svako pitanje  sve potrebne podatke (pitanje, točan odgovor, 3 netočna odgovora)
     */
    @Test
    fun testQuizQuestionsByCategory() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val kategorije = listOf("Planeti", "Sunce", "Mjeseci", "Asteroidi", "Kometi", "Objekti")

        kategorije.forEach { kategorija ->
            val pitanja = kvizPitanjeDao.getByKategorija(kategorija)
            assertTrue("Nema pitanja za kategoriju $kategorija", pitanja.isNotEmpty())

            pitanja.forEach { pitanje ->
                assertFalse("Pitanje je prazno u kategoriji $kategorija", pitanje.pitanje.isBlank())
                assertFalse("Točan odgovor je prazan", pitanje.tocniOdgovor.isBlank())
                assertFalse("Netočan odgovor 1 je prazan", pitanje.netocniOdgovor1.isBlank())
                assertFalse("Netočan odgovor 2 je prazan", pitanje.netocniOdgovor2.isBlank())
                assertFalse("Netočan odgovor 3 je prazan", pitanje.netocniOdgovor3.isBlank())
                assertEquals("Kategorija se ne slaže", kategorija, pitanje.kategorija)
            }
        }
    }

    /**
     * test provjerava spremanje kviz rezultata u bazu
     * i dohvaćaju li se rezultati ispravno po korisniku
     */
    @Test
    fun testQuizResultsAndBestScores() = runBlocking {
        val testKorisnik = Korisnik(
            ime = "Quiz Master",
            email = "quizmaster@test.com",
            password = "password"
        )
        val userId = korisnikDao.insert(testKorisnik).toInt()

        val kvizId = "Planeti"

        kvizRezultatDao.insert(KvizRezultat(
            userId = userId,
            kvizId = kvizId,
            najboljiRezultat = 60
        ))

        kvizRezultatDao.insert(KvizRezultat(
            userId = userId,
            kvizId = kvizId,
            najboljiRezultat = 80
        ))

        kvizRezultatDao.insert(KvizRezultat(
            userId = userId,
            kvizId = kvizId,
            najboljiRezultat = 70
        ))

        val rezultati = kvizRezultatDao.getByUserId(userId)
        val planetiRezultat = rezultati.find { it.kvizId == kvizId }

        assertNotNull("Rezultat za Planete trebao bi postojati", planetiRezultat)
    }

    /**
     * test provjerava da svako kviz pitanje ima 4 različita odgovora
     * (1 točan + 3 netočna) i da nema duplikata među odgovorima
     */
    @Test
    fun testQuizAnswersUniqueness() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val svaPitanja = kvizPitanjeDao.getAll()

        svaPitanja.forEach { pitanje ->
            val odgovori = listOf(
                pitanje.tocniOdgovor,
                pitanje.netocniOdgovor1,
                pitanje.netocniOdgovor2,
                pitanje.netocniOdgovor3
            )

            val uniqueOdgovori = odgovori.distinct()
            assertEquals(
                "Pitanje ID ${pitanje.id} ima duplikate odgovore: $odgovori",
                4, uniqueOdgovori.size
            )

            odgovori.forEach { odgovor ->
                assertFalse("Odgovor ne smije biti prazan", odgovor.isBlank())
            }
        }
    }

    /**
     * test simulira igranje kviza - uzima 5 pitanja, simulira točne odgovore
     * na prva 3 pitanja i sprema rezultat u bazu
     */
    @Test
    fun testQuizGameLogic() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val testKorisnik = Korisnik(
            ime = "Quiz Player",
            email = "player@test.com",
            password = "password"
        )
        val userId = korisnikDao.insert(testKorisnik).toInt()

        val kategorija = "Planeti"
        val pitanja = kvizPitanjeDao.getByKategorija(kategorija)

        assertTrue("Trebaju postojati pitanja za testiranje", pitanja.isNotEmpty())

        val kvizPitanja = pitanja.take(5)
        var score = 0

        kvizPitanja.forEachIndexed { index, pitanje ->
            if (index < 3) {
                val odgovor = pitanje.tocniOdgovor
                assertEquals("Odgovor trebao bi biti točan", pitanje.tocniOdgovor, odgovor)
                score++
            }
        }

        kvizRezultatDao.insert(KvizRezultat(
            userId = userId,
            kvizId = kategorija,
            najboljiRezultat = score
        ))

        val rezultati = kvizRezultatDao.getByUserId(userId)
        assertTrue("Trebao bi postojati rezultat", rezultati.isNotEmpty())
        val rezultat = rezultati.first()
        assertEquals("Score trebao bi biti 3", 3, rezultat.najboljiRezultat)
    }

    /**
     * test provjerava da sve očekivane kategorije kviza imaju
     * dovoljno pitanja (minimum 5 po kategoriji)
     */
    @Test
    fun testQuizCategoriesHaveEnoughQuestions() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val expectedCategories = listOf("Planeti", "Sunce", "Mjeseci", "Asteroidi", "Kometi", "Objekti")
        val minQuestionsPerCategory = 5

        expectedCategories.forEach { kategorija ->
            val pitanja = kvizPitanjeDao.getByKategorija(kategorija)
            assertTrue(
                "Kategorija $kategorija ima samo ${pitanja.size} pitanja, a trebala bi imati barem $minQuestionsPerCategory",
                pitanja.size >= minQuestionsPerCategory
            )
        }
    }

    /**
     * test provjerava da se kviz rezultati različitih korisnika
     * ne miješaju i da svaki korisnik vidi samo svoje rezultate
     */
    @Test
    fun testMultipleUsersQuizResults() = runBlocking {
        val korisnik1 = Korisnik(ime = "User1", email = "user1@test.com", password = "pass1")
        val korisnik2 = Korisnik(ime = "User2", email = "user2@test.com", password = "pass2")

        val userId1 = korisnikDao.insert(korisnik1).toInt()
        val userId2 = korisnikDao.insert(korisnik2).toInt()

        kvizRezultatDao.insert(KvizRezultat(
            userId = userId1,
            kvizId = "Planeti",
            najboljiRezultat = 85
        ))
        kvizRezultatDao.insert(KvizRezultat(
            userId = userId1,
            kvizId = "Sunce",
            najboljiRezultat = 90
        ))
        kvizRezultatDao.insert(KvizRezultat(
            userId = userId2,
            kvizId = "Planeti",
            najboljiRezultat = 75
        ))
        kvizRezultatDao.insert(KvizRezultat(
            userId = userId2,
            kvizId = "Mjeseci",
            najboljiRezultat = 80
        ))

        val rezultati1 = kvizRezultatDao.getByUserId(userId1)
        val rezultati2 = kvizRezultatDao.getByUserId(userId2)

        assertEquals("Korisnik 1 trebao bi imati 2 rezultata", 2, rezultati1.size)
        assertEquals("Korisnik 2 trebao bi imati 2 rezultata", 2, rezultati2.size)

        assertTrue("Svi rezultati korisnika 1 trebaju biti njegovi",
            rezultati1.all { it.userId == userId1 })
        assertTrue("Svi rezultati korisnika 2 trebaju biti njegovi",
            rezultati2.all { it.userId == userId2 })
    }

    /**
     * test provjerava da su odgovori na kviz pitanja logični
     * (npr. da je odgovor na "najmanji planet" stvarno "Merkur")
     */
    @Test
    fun testQuizAnswersLogic() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val planetiPitanja = kvizPitanjeDao.getByKategorija("Planeti")

        planetiPitanja.forEach { pitanje ->
            when {
                pitanje.pitanje.contains("najmanji planet") -> {
                    assertEquals("Odgovor na najmanji planet trebao bi biti Merkur",
                        "Merkur", pitanje.tocniOdgovor)
                }
                pitanje.pitanje.contains("najveći planet") -> {
                    assertEquals("Odgovor na najveći planet trebao bi biti Jupiter",
                        "Jupiter", pitanje.tocniOdgovor)
                }
                pitanje.pitanje.contains("Crveni planet") -> {
                    assertEquals("Odgovor na Crveni planet trebao bi biti Mars",
                        "Mars", pitanje.tocniOdgovor)
                }
            }
        }
    }

    /**
     * test provjerava performanse kviz funkcionalnosti -
     * da dohvaćanje pitanja i filtriranje po kategoriji radi brzo
     */
    @Test
    fun testQuizPerformanceWithManyQuestions() = runBlocking {
        DatabaseInitializer.initDatabase(db)

        val startTime = System.currentTimeMillis()
        val svaPitanja = kvizPitanjeDao.getAll()
        val endTime = System.currentTimeMillis()
        val queryTime = endTime - startTime

        assertTrue("Dohvaćanje svih pitanja traje predugo: ${queryTime}ms", queryTime < 500)
        assertTrue("Trebaju postojati pitanja", svaPitanja.isNotEmpty())

        val filterStartTime = System.currentTimeMillis()
        val planetiPitanja = kvizPitanjeDao.getByKategorija("Planeti")
        val filterEndTime = System.currentTimeMillis()
        val filterTime = filterEndTime - filterStartTime

        assertTrue("Filtriranje po kategoriji traje predugo: ${filterTime}ms", filterTime < 200)
        assertTrue("Trebaju postojati pitanja o planetima", planetiPitanja.isNotEmpty())
    }

    /**
     * test provjerava funkcionalnost ažuriranja najboljeg rezultata -
     * da se rezultat ažurira samo ako je novi rezultat bolji od postojećeg
     */
    @Test
    fun testUpdateBestScore() = runBlocking {
        val testKorisnik = Korisnik(
            ime = "Score Updater",
            email = "updater@test.com",
            password = "password"
        )
        val userId = korisnikDao.insert(testKorisnik).toInt()
        val kvizId = "Planeti"

        kvizRezultatDao.insert(KvizRezultat(
            userId = userId,
            kvizId = kvizId,
            najboljiRezultat = 60
        ))

        kvizRezultatDao.updateRezultat(userId, kvizId, 85, "2025-06-14 18:37:00")

        val rezultati = kvizRezultatDao.getByUserId(userId)
        val planetiRezultat = rezultati.find { it.kvizId == kvizId }

        assertNotNull("Rezultat trebao bi postojati", planetiRezultat)
        assertEquals("Rezultat trebao bi biti ažuriran na 85", 85, planetiRezultat?.najboljiRezultat)
    }
}