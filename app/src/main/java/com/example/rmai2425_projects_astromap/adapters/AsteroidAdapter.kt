package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.AsteroidDetailActivity
import com.example.rmai2425_projects_astromap.database.Asteroid

class AsteroidAdapter(
    private val asteroids: List<Asteroid>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<AsteroidAdapter.MyViewHolder>() {

    private val uniqueAsteroids = asteroids.distinctBy { it.ime.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.asteroid_title)
        val asteroidImg: ImageView = view.findViewById(R.id.asteroid_img)
        val menuIcon: ImageView = view.findViewById(R.id.asteroid_menu_icon)
        val asteroidInfo: TextView = view.findViewById(R.id.asteroidinfo)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_asteroid, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniqueAsteroids.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val asteroid = uniqueAsteroids[position]
        holder.title.text = asteroid.ime
        holder.asteroidInfo.text = asteroid.kratkiOpis
        val context = holder.itemView.context
        val dummyImage = when (asteroid.ime.trim().lowercase()) {
            "ceres" -> R.drawable.ceres
            "vesta" -> R.drawable.vesta1
            "eros" -> R.drawable.eros
            "itokawa" -> R.drawable.itokawa
            "pallas" -> R.drawable.pallas
            else -> R.drawable.ceres
        }
        holder.asteroidImg.setImageResource(dummyImage)
        holder.menuIcon.setOnClickListener {
            val intent = Intent(context, AsteroidDetailActivity::class.java).apply {
                putExtra("ime", asteroid.ime)
                putExtra("detaljanOpis", asteroid.detaljanOpis)
                putExtra("smjestaj", asteroid.smjestaj)
                putExtra("sastav", asteroid.sastav)
                putExtra("imgRes", dummyImage)
            }
            context.startActivity(intent)
        }

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(asteroid.ime)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(asteroid.ime)
                    markModuleCompleted(asteroid.ime)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniqueAsteroids.indexOfFirst { it.ime == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}