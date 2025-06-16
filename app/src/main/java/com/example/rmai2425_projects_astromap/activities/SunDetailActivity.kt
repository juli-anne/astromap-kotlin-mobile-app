package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rmai2425_projects_astromap.R

class SunDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun_detail)

        val title: TextView = findViewById(R.id.sun_title_info)
        val image: ImageView = findViewById(R.id.sun_img_detail)
        val opis: TextView = findViewById(R.id.sun_detailed_desc)
        val povrsinskaTemp: TextView = findViewById(R.id.sun_surface_temp)
        val jezgraTemp: TextView = findViewById(R.id.sun_core_temp)
        val masa: TextView = findViewById(R.id.sun_mass)
        val sastav: TextView = findViewById(R.id.sun_composition)

        val extras = intent.extras
        if (extras != null) {
            title.text = extras.getString("ime") ?: "Sunce"
            opis.text = extras.getString("detaljanOpis") ?: "Nema opisa"
            povrsinskaTemp.text = "Površinska temperatura: ${extras.getString("povrsinskaTemperatura") ?: "Nepoznato"}"
            jezgraTemp.text = "Temperatura jezgre: ${extras.getString("temperaturaJezgre") ?: "Nepoznato"}"
            masa.text = "Masa: ${extras.getDouble("masa", 0.0)} kg"
            sastav.text = "Sastav: ${extras.getString("sastav") ?: "Nepoznat"}"
            image.setImageResource(extras.getInt("imgRes", R.drawable.sunce))
        }

        val toolbar: Toolbar = findViewById(R.id.sun_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
