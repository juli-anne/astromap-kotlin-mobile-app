package com.example.rmai2425_projects_astromap.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rmai2425_projects_astromap.R

class ObjectDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_detail)


        val toolbar: Toolbar = findViewById(R.id.object_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        val title: TextView = findViewById(R.id.object_title_info)
        val image: ImageView = findViewById(R.id.object_img_detail)
        val type: TextView = findViewById(R.id.object_type)
        val placement: TextView = findViewById(R.id.object_placement)
        val description: TextView = findViewById(R.id.object_description)
        val facts: TextView = findViewById(R.id.object_facts)
        val size: TextView = findViewById(R.id.object_size)
        val composition: TextView = findViewById(R.id.object_composition)


        val extras = intent.extras
        if (extras != null) {
            title.text = extras.getString("ime") ?: "Nepoznato ime"
            image.setImageResource(extras.getInt("imgRes", R.drawable.pluton))

            description.text = "${extras.getString("opis") ?: "Nema opisa"}"
            type.text = "Tip: ${extras.getString("tip") ?: "N/A"}"
            placement.text = "Smještaj: ${extras.getString("smjestaj") ?: "N/A"}"
            facts.text = "Zanimljivost: ${extras.getString("zanimljivosti") ?: "N/A"}"
            size.text = "Veličina: ${extras.getString("velicina") ?: "N/A"}"
            composition.text = "Sastav: ${extras.getString("sastav") ?: "N/A"}"
        }
    }
}
