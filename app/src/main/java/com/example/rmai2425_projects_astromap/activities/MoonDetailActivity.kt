package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rmai2425_projects_astromap.R

class MoonDetailActivity : AppCompatActivity() {
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
}
