package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rmai2425_projects_astromap.R

class AsteroidDetailActivity : AppCompatActivity() {
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
}
