package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rmai2425_projects_astromap.R

class PlanetDetailActivity : AppCompatActivity() {
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
            title.text = extras.getString("ime")
            opis.text = extras.getString("detaljanOpis")
            danTemp.text = "Temperatura danju: ${extras.getString("danTemp")} "
            nocTemp.text = "Temperatura noću: ${extras.getString("nocTemp")} "
            promjer.text = "Promjer: ${extras.getDouble("promjer")} km"
            mjeseci.text = if (extras.getBoolean("imaMjesec")) "Ima mjesec(e)" else "Nema mjeseca"
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
}
