package com.example.rmai2425_projects_astromap.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.utils.*

class ConstellationDetailActivity : AppCompatActivity() {

    private val constellationOrder = listOf("lovac", "veliki medvjed", "maleni medvjed", "kasiopeja", "lav", "vaga", "škorpion", "strijelac", "kentaur", "pješčani sat")
    private var currentConstellationName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation_detail)

        val title: TextView = findViewById(R.id.constellation_title_info)
        val imeLat: TextView = findViewById(R.id.constellation_lat_name)
        val image: ImageView = findViewById(R.id.constellation_img_detail)
        val position: TextView = findViewById(R.id.constellation_position)
        val importance: TextView = findViewById(R.id.constellation_importance)
        val stars: TextView = findViewById(R.id.constellation_stars)

        val extras = intent.extras
        if (extras != null) {
            currentConstellationName = extras.getString("ime", "").lowercase()
            title.text = extras.getString("ime")
            imeLat.text = "Latinski naziv: ${extras.getString("imeLat")}"
            position.text = "Pozicija: ${extras.getString("pozicija")}"
            importance.text = "Značaj: ${extras.getString("znacaj")}"
            stars.text = "Najsjajnije zvijezde: ${extras.getString("svjetleZvijezde")}"
            image.setImageResource(extras.getInt("imgRes"))
        }

        val toolbar: Toolbar = findViewById(R.id.constellation_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentIndex = constellationOrder.indexOf(currentConstellationName)
        if (currentIndex != -1 && currentIndex < constellationOrder.size - 1) {
            menuInflater.inflate(R.menu.detail_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                navigateToNextConstellation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNextConstellation() {
        val currentIndex = constellationOrder.indexOf(currentConstellationName)
        if (currentIndex != -1 && currentIndex < constellationOrder.size - 1) {
            val nextConstellation = constellationOrder[currentIndex + 1]
            val nextConstellationData = getConstellationData(nextConstellation)

            val intent = Intent(this, ConstellationDetailActivity::class.java).apply {
                putExtra("ime", nextConstellationData.first)
                putExtra("imeLat", nextConstellationData.second)
                putExtra("pozicija", nextConstellationData.third)
                putExtra("znacaj", nextConstellationData.fourth)
                putExtra("svjetleZvijezde", nextConstellationData.fifth)
                putExtra("imgRes", nextConstellationData.sixth)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getConstellationData(constellationName: String): Sextuple<String, String, String, String, String, Int> {
        return when (constellationName) {
            "lovac" -> Sextuple("Lovac", "Lovac", "Ekvatorijalna", "Navigacija", "Betelgeuse, Rigel", R.drawable.orion)
            "veliki medvjed" -> Sextuple("Veliki medvjed", "Ursa Major", "Sjeverna hemisfera", "Navigacija", "Dubhe, Merak", R.drawable.ursa_major)
            "maleni medvjed" -> Sextuple("Maleni medvjed", "Ursa Minor", "Sjeverna hemisfera", "Polarna zvijezda", "Polaris", R.drawable.ursa_minor)
            "kasiopeja" -> Sextuple("Kasiopeja", "Cassiopeia", "Sjeverna hemisfera", "Mitologija", "Schedar, Caph", R.drawable.cassiopeia)
            "lav" -> Sextuple("Lav", "Leo", "Ekvatorijalna", "Zodijak", "Regulus", R.drawable.leo)
            "vaga" -> Sextuple("Vaga", "Libra", "Južna hemisfera", "Zodijak", "Zubenelgenubi", R.drawable.libra)
            "škorpion" -> Sextuple("Škorpion", "Scorpius", "Južna hemisfera", "Zodijak", "Antares", R.drawable.scorpius)
            "strijelac" -> Sextuple("Strijelac", "Sagittarius", "Južna hemisfera", "Centar galaksije", "Kaus Australis", R.drawable.sagittarius)
            "kentaur" -> Sextuple("Kentaur", "Centaurus", "Južna hemisfera", "Najbliže zvijezde", "Alpha Centauri", R.drawable.centaurus)
            "pješčani sat" -> Sextuple("Pješčani sat", "Hourglass", "Ekvatorijalna", "Navigacija", "Betelgeuse, Rigel", R.drawable.hourglass)
            else -> Sextuple("", "", "", "", "", 0)
        }
    }
}