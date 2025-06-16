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

class CometDetailActivity : AppCompatActivity() {

    private val cometOrder = listOf("halleyjev komet", "hale-bopp", "neowise", "tempel 1", "enckeov komet")
    private var currentCometName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comet_detail)

        val title: TextView = findViewById(R.id.comet_title_info)
        val image: ImageView = findViewById(R.id.comet_img_detail)
        val opis: TextView = findViewById(R.id.comet_detailed_desc)
        val orbitalPeriod: TextView = findViewById(R.id.orbital_period)
        val lastPerihel: TextView = findViewById(R.id.last_perihel)
        val nextPerihel: TextView = findViewById(R.id.next_perihel)
        val coreSize: TextView = findViewById(R.id.core_size)

        val extras = intent.extras
        if (extras != null) {
            currentCometName = extras.getString("ime", "").lowercase()
            title.text = extras.getString("ime")
            opis.text = extras.getString("kratkiOpis")
            orbitalPeriod.text = "Orbitalni period: ${extras.getInt("orbitalniPeriod")} godina"
            lastPerihel.text = "Zadnji perihel: ${extras.getString("posljednjiPerihel")} g."
            nextPerihel.text = "Sljedeći perihel: ${extras.getString("sljedeciPerihel")} g."
            coreSize.text = "Veličina jezgre: ${extras.getDouble("velicinaJezgre")} km"
            image.setImageResource(extras.getInt("imgRes"))
        }

        val toolbar: Toolbar = findViewById(R.id.comet_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentIndex = cometOrder.indexOf(currentCometName)
        if (currentIndex != -1 && currentIndex < cometOrder.size - 1) {
            menuInflater.inflate(R.menu.detail_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                navigateToNextComet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNextComet() {
        val currentIndex = cometOrder.indexOf(currentCometName)
        if (currentIndex != -1 && currentIndex < cometOrder.size - 1) {
            val nextComet = cometOrder[currentIndex + 1]
            val nextCometData = getCometData(nextComet)

            val intent = Intent(this, CometDetailActivity::class.java).apply {
                putExtra("ime", nextCometData.first)
                putExtra("kratkiOpis", nextCometData.second)
                putExtra("orbitalniPeriod", nextCometData.third)
                putExtra("posljednjiPerihel", nextCometData.fourth)
                putExtra("sljedeciPerihel", nextCometData.fifth)
                putExtra("velicinaJezgre", nextCometData.sixth)
                putExtra("imgRes", nextCometData.seventh)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getCometData(cometName: String): Septuple<String, String, Int, String, String, Double, Int> {
        return when (cometName) {
            "hale-bopp" -> Septuple("Hale-Bopp", "Veliki komet...", 2533, "1997", "4530", 40.0, R.drawable.halebopp)
            "neowise" -> Septuple("NEOWISE", "Nedavno otkriveni komet...", 6800, "2020", "8820", 5.0, R.drawable.neowise)
            "tempel 1" -> Septuple("Tempel 1", "Komet koji je proučavan...", 5, "2005", "2011", 6.0, R.drawable.tempel1)
            "enckeov komet" -> Septuple("Enckeov komet", "Komet s najkraćim periodom...", 3, "2020", "2024", 4.8, R.drawable.encke)
            else -> Septuple("", "", 0, "", "", 0.0, 0)
        }
    }
}