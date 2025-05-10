package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.PlanetDetailActivity
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.database.Planet

class PlanetAdapter(private val planets: List<Planet>) : RecyclerView.Adapter<PlanetAdapter.MyViewHolder>() {

    private val uniquePlanets = planets.distinctBy { it.id }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val planetImg: ImageView = view.findViewById(R.id.planet_img)
        val menuIcon: ImageView = view.findViewById(R.id.menu_icon)
        val planetInfo: TextView = view.findViewById(R.id.planetinfo)
        val diameterIcon: ImageView = view.findViewById(R.id.diameter_icon)
        val planetDiameter: TextView = view.findViewById(R.id.planet_diameter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniquePlanets.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val planet = uniquePlanets[position]
        holder.title.text = planet.ime
        holder.planetInfo.text = planet.kratkiOpis
        holder.planetDiameter.text = "${planet.promjer} km"

        val context = holder.itemView.context

        val dummyImage = when (planet.ime.trim().lowercase()) {
            "merkur" -> R.drawable.mercury
            "venera" -> R.drawable.venus
            "zemlja" -> R.drawable.earth
            "mars" -> R.drawable.mars
            "jupiter" -> R.drawable.jupiter
            "saturn" -> R.drawable.saturn
            "uran" -> R.drawable.uranus
            "neptun" -> R.drawable.neptune
            else -> R.drawable.earth
        }

        holder.planetImg.setImageResource(dummyImage)

        // Klik na menu_icon otvara detalje
        holder.menuIcon.setOnClickListener {
            val intent = Intent(context, PlanetDetailActivity::class.java).apply {
                putExtra("ime", planet.ime)
                putExtra("detaljanOpis", planet.detaljanOpis)
                putExtra("danTemp", planet.povrsinskaTemperaturaDan)
                putExtra("nocTemp", planet.povrsinskaTemperaturaNoc)
                putExtra("promjer", planet.promjer)
                putExtra("imaMjesec", planet.imaMjesec)
                putExtra("imgRes", dummyImage)
            }
            context.startActivity(intent)
        }
    }
}
