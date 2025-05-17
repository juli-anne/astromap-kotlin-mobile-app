package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rmai2425_projects_astromap.R

class ConstellationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation_detail)

        val title: TextView = findViewById(R.id.constellation_title_info)
        val image: ImageView = findViewById(R.id.constellation_img_detail)
        val position: TextView = findViewById(R.id.constellation_position)
        val importance: TextView = findViewById(R.id.constellation_importance)
        val stars: TextView = findViewById(R.id.constellation_stars)

        val extras = intent.extras
        if (extras != null) {
            title.text = extras.getString("ime")
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
}
