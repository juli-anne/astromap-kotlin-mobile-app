package com.example.rmai2425_projects_astromap.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.utils.*

class PlanetDetailActivity : AppCompatActivity() {

    private val planetOrder = listOf("merkur", "venera", "zemlja", "mars", "jupiter", "saturn", "uran", "neptun")
    private var currentPlanetName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_detail)

        val title: TextView = findViewById(R.id.title_info)
        val image: ImageView = findViewById(R.id.planet_img_info)
        val opis: TextView = findViewById(R.id.opis_text)
        val danTemp: TextView = findViewById(R.id.temp_dan)
        val nocTemp: TextView = findViewById(R.id.temp_noc)
        val promjer: TextView = findViewById(R.id.promjer_text)
        val mjeseci: TextView = findViewById(R.id.mjeseci_text)

        val extras = intent.extras
        if (extras != null) {
            currentPlanetName = extras.getString("ime", "").lowercase()
            title.text = extras.getString("ime")
            opis.text = extras.getString("detaljanOpis")
            danTemp.text = "Temperatura danju: ${extras.getString("danTemp")}"
            nocTemp.text = "Temperatura noću: ${extras.getString("nocTemp")}"
            promjer.text = "Promjer: ${extras.getDouble("promjer")} km"
            mjeseci.text = if (extras.getBoolean("imaMjesec")) "Ima mjesece" else "Nema mjeseca"
            image.setImageResource(extras.getInt("imgRes"))
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentIndex = planetOrder.indexOf(currentPlanetName)
        if (currentIndex != -1 && currentIndex < planetOrder.size - 1) {
            menuInflater.inflate(R.menu.detail_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                navigateToNextPlanet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNextPlanet() {
        val currentIndex = planetOrder.indexOf(currentPlanetName)
        if (currentIndex != -1 && currentIndex < planetOrder.size - 1) {
            val nextPlanet = planetOrder[currentIndex + 1]
            val nextPlanetData = getPlanetData(nextPlanet)

            val intent = Intent(this, PlanetDetailActivity::class.java).apply {
                putExtra("ime", nextPlanetData.first)
                putExtra("detaljanOpis", nextPlanetData.second)
                putExtra("danTemp", nextPlanetData.third)
                putExtra("nocTemp", nextPlanetData.fourth)
                putExtra("promjer", nextPlanetData.fifth)
                putExtra("imaMjesec", nextPlanetData.sixth)
                putExtra("imgRes", nextPlanetData.seventh)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getPlanetData(planetName: String): Septuple<String, String, String, String, Double, Boolean, Int> {
        return when (planetName) {
            "venera" -> Septuple("Venera", "Druga planeta od Sunca...", "462°C", "462°C", 12104.0, false, R.drawable.venus)
            "zemlja" -> Septuple("Zemlja", "Treća planeta od Sunca...", "15°C", "15°C", 12756.0, true, R.drawable.zemlja)
            "mars" -> Septuple("Mars", "Četvrta planeta od Sunca...", "20°C", "-87°C", 6792.0, true, R.drawable.mars)
            "jupiter" -> Septuple("Jupiter", "Peta planeta od Sunca...", "-110°C", "-110°C", 142984.0, true, R.drawable.jupiter)
            "saturn" -> Septuple("Saturn", "Šesta planeta od Sunca...", "-140°C", "-140°C", 120536.0, true, R.drawable.saturn)
            "uran" -> Septuple("Uran", "Sedma planeta od Sunca...", "-195°C", "-195°C", 51118.0, true, R.drawable.uranus)
            "neptun" -> Septuple("Neptun", "Osma planeta od Sunca...", "-200°C", "-200°C", 49528.0, true, R.drawable.neptune)
            else -> Septuple("", "", "", "", 0.0, false, 0)
        }
    }
}