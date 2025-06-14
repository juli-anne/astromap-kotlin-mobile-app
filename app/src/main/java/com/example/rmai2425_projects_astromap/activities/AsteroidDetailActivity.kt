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

class AsteroidDetailActivity : AppCompatActivity() {

    private val asteroidOrder = listOf("ceres", "vesta", "eros", "itokawa", "pallas")
    private var currentAsteroidName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asteroid_detail)

        val title: TextView = findViewById(R.id.asteroid_title_info)
        val image: ImageView = findViewById(R.id.asteroid_img_detail)
        val opis: TextView = findViewById(R.id.asteroid_detailed_desc)
        val smjestaj: TextView = findViewById(R.id.asteroid_placement)
        val sastav: TextView = findViewById(R.id.asteroid_composition)

        val extras = intent.extras
        if (extras != null) {
            currentAsteroidName = extras.getString("ime", "").lowercase()
            title.text = extras.getString("ime")
            opis.text = extras.getString("detaljanOpis")
            smjestaj.text = "Smještaj: ${extras.getString("smjestaj")}"
            sastav.text = "Sastav: ${extras.getString("sastav")}"
            image.setImageResource(extras.getInt("imgRes"))
        }

        val toolbar: Toolbar = findViewById(R.id.asteroid_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentIndex = asteroidOrder.indexOf(currentAsteroidName)
        if (currentIndex != -1 && currentIndex < asteroidOrder.size - 1) {
            menuInflater.inflate(R.menu.detail_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                navigateToNextAsteroid()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNextAsteroid() {
        val currentIndex = asteroidOrder.indexOf(currentAsteroidName)
        if (currentIndex != -1 && currentIndex < asteroidOrder.size - 1) {
            val nextAsteroid = asteroidOrder[currentIndex + 1]
            val nextAsteroidData = getAsteroidData(nextAsteroid)

            val intent = Intent(this, AsteroidDetailActivity::class.java).apply {
                putExtra("ime", nextAsteroidData.first)
                putExtra("detaljanOpis", nextAsteroidData.second)
                putExtra("smjestaj", nextAsteroidData.third)
                putExtra("sastav", nextAsteroidData.fourth)
                putExtra("imgRes", nextAsteroidData.fifth)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getAsteroidData(asteroidName: String): Quintuple<String, String, String, String, Int> {
        return when (asteroidName) {
            "vesta" -> Quintuple("Vesta", "Drugi najveći asteroid...", "Glavni pojas", "Silikatni", R.drawable.vesta1)
            "eros" -> Quintuple("Eros", "Asteroid blizu Zemlje...", "Blizu Zemlje", "Silikatni", R.drawable.eros)
            "itokawa" -> Quintuple("Itokawa", "Mali asteroid...", "Blizu Zemlje", "Silikatni", R.drawable.itokawa)
            "pallas" -> Quintuple("Pallas", "Treći najveći asteroid...", "Glavni pojas", "Silikatni", R.drawable.pallas)
            else -> Quintuple("", "", "", "", 0)
        }
    }
}