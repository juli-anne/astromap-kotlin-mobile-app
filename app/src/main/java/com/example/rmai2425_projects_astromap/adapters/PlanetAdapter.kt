package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.activities.PlanetDetailActivity
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.database.Planet

class PlanetAdapter(
    private val planets: List<Planet>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<PlanetAdapter.MyViewHolder>() {

    private val uniquePlanets = planets.distinctBy { it.ime.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val planetImg: ImageView = view.findViewById(R.id.planet_img)
        val menuIcon: ImageView = view.findViewById(R.id.menu_icon)
        val planetInfo: TextView = view.findViewById(R.id.planetinfo)
        val diameterIcon: ImageView = view.findViewById(R.id.diameter_icon)
        val planetDiameter: TextView = view.findViewById(R.id.planet_diameter)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_planet, parent, false)
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
            "zemlja" -> R.drawable.zemlja
            "mars" -> R.drawable.mars
            "jupiter" -> R.drawable.jupiter
            "saturn" -> R.drawable.saturn
            "uran" -> R.drawable.uranus
            "neptun" -> R.drawable.neptune
            else -> R.drawable.earth
        }
        holder.planetImg.setImageResource(dummyImage)
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

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(planet.ime)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(planet.ime)
                    markModuleCompleted(planet.ime)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniquePlanets.indexOfFirst { it.ime == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}