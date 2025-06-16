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

class ObjectDetailActivity : AppCompatActivity() {

    private val objectOrder = listOf("pluton", "ceres", "meteroid", "meteor", "meteorit", "svemirska prasina")
    private var currentObjectName: String = ""

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
            currentObjectName = extras.getString("ime", "").lowercase()
            title.text = extras.getString("ime") ?: "Nepoznato ime"
            image.setImageResource(extras.getInt("imgRes", R.drawable.pluton))
            description.text = extras.getString("opis") ?: "Nema opisa"
            type.text = "Tip: ${extras.getString("tip") ?: "N/A"}"
            placement.text = "Smještaj: ${extras.getString("smjestaj") ?: "N/A"}"
            facts.text = "Zanimljivost: ${extras.getString("zanimljivosti") ?: "N/A"}"
            size.text = "Veličina: ${extras.getString("velicina") ?: "N/A"}"
            composition.text = "Sastav: ${extras.getString("sastav") ?: "N/A"}"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentIndex = objectOrder.indexOf(currentObjectName)
        if (currentIndex != -1 && currentIndex < objectOrder.size - 1) {
            menuInflater.inflate(R.menu.detail_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                navigateToNextObject()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNextObject() {
        val currentIndex = objectOrder.indexOf(currentObjectName)
        if (currentIndex != -1 && currentIndex < objectOrder.size - 1) {
            val nextObject = objectOrder[currentIndex + 1]
            val nextObjectData = getObjectData(nextObject)

            val intent = Intent(this, ObjectDetailActivity::class.java).apply {
                putExtra("ime", nextObjectData.first)
                putExtra("opis", nextObjectData.second)
                putExtra("tip", nextObjectData.third)
                putExtra("smjestaj", nextObjectData.fourth)
                putExtra("zanimljivosti", nextObjectData.fifth)
                putExtra("velicina", nextObjectData.sixth)
                putExtra("sastav", nextObjectData.seventh)
                putExtra("imgRes", nextObjectData.eighth)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun getObjectData(objectName: String): Octuple<String, String, String, String, String, String, String, Int> {
        return when (objectName) {
            "ceres" -> Octuple("Ceres", "Patuljasta planeta...", "Patuljasta planeta", "Glavni pojas", "Najveći asteroid", "940 km", "Silikatni", R.drawable.ceres)
            "meteroid" -> Octuple("Meteroid", "Mali objekt u svemiru...", "Meteroid", "Svemir", "Postaju meteori", "< 1m", "Kameniti", R.drawable.meteoroid)
            "meteor" -> Octuple("Meteor", "Meteroid u atmosferi...", "Meteor", "Atmosfera", "Svjetleći trag", "Varijabilno", "Kameniti", R.drawable.meteor)
            "meteorit" -> Octuple("Meteorit", "Meteor koji je pao...", "Meteorit", "Zemlja", "Preživio pad", "Varijabilno", "Kameniti/Metalni", R.drawable.meteorite)
            "svemirska prasina" -> Octuple("Svemirska prasina", "Sitne čestice...", "Prasina", "Svemir", "Svugdje prisutna", "< 1mm", "Silikatni", R.drawable.space_dust)
            else -> Octuple("", "", "", "", "", "", "", 0)
        }
    }
}