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

class MoonDetailActivity : AppCompatActivity() {

    private val moonOrder = listOf("mjesec", "europa", "titan", "ganimed", "io", "triton", "miranda")
    private var currentMoonName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moon_detail)

        val title: TextView = findViewById(R.id.moon_title_info)
        val image: ImageView = findViewById(R.id.moon_img_detail)
        val opis: TextView = findViewById(R.id.moon_detailed_desc)
        val planetName: TextView = findViewById(R.id.moon_planet_name)
        val velicina: TextView = findViewById(R.id.size)
        val zanimljivosti: TextView = findViewById(R.id.moon_facts)

        val extras = intent.extras
        if (extras != null) {
            currentMoonName = extras.getString("ime", "").lowercase()
            title.text = extras.getString("ime")
            opis.text = extras.getString("detaljanOpis")
            planetName.text = "Planet: ${extras.getString("planetIme")}"
            velicina.text = "Veličina: ${extras.getDouble("velicina")} km"
            zanimljivosti.text = "Zanimljivosti: ${extras.getString("zanimljivosti")}"
            image.setImageResource(extras.getInt("imgRes"))
        }

        val toolbar: Toolbar = findViewById(R.id.moon_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentIndex = moonOrder.indexOf(currentMoonName)
        if (currentIndex != -1 && currentIndex < moonOrder.size - 1) {
            menuInflater.inflate(R.menu.detail_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                navigateToNextMoon()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNextMoon() {
        val currentIndex = moonOrder.indexOf(currentMoonName)
        if (currentIndex != -1 && currentIndex < moonOrder.size - 1) {
            val nextMoon = moonOrder[currentIndex + 1]
            val nextMoonData = getMoonData(nextMoon)

            val intent = Intent(this, MoonDetailActivity::class.java).apply {
                putExtra("ime", nextMoonData.first)
                putExtra("detaljanOpis", nextMoonData.second)
                putExtra("planetIme", nextMoonData.third)
                putExtra("velicina", nextMoonData.fourth)
                putExtra("zanimljivosti", nextMoonData.fifth)
                putExtra("imgRes", nextMoonData.sixth)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getMoonData(moonName: String): Sextuple<String, String, String, Double, String, Int> {
        return when (moonName) {
            "europa" -> Sextuple("Europa", "Mjesec Jupitera...", "Jupiter", 3121.6, "Ima ocean pod ledom", R.drawable.europamoon)
            "titan" -> Sextuple("Titan", "Najveći mjesec Saturna...", "Saturn", 5149.5, "Ima atmosferu", R.drawable.titanmoon)
            "ganimed" -> Sextuple("Ganimed", "Najveći mjesec u Sunčevom sustavu...", "Jupiter", 5268.2, "Veći od Merkura", R.drawable.ganimedmoon)
            "io" -> Sextuple("Io", "Vulkanski mjesec Jupitera...", "Jupiter", 3643.2, "Aktivni vulkani", R.drawable.iomoon)
            "triton" -> Sextuple("Triton", "Najveći mjesec Neptuna...", "Neptun", 2706.8, "Retrogradan orbit", R.drawable.tritonmoon)
            "miranda" -> Sextuple("Miranda", "Mjesec Urana...", "Uran", 471.6, "Neobična površina", R.drawable.mirandamoon)
            else -> Sextuple("", "", "", 0.0, "", 0)
        }
    }
}