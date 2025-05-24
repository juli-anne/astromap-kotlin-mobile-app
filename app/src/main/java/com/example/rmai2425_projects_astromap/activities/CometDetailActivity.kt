package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rmai2425_projects_astromap.R

class CometDetailActivity : AppCompatActivity() {
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
}
