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

class AsteroidAdapter(private val asteroids: List<Asteroid>) :
    RecyclerView.Adapter<AsteroidAdapter.MyViewHolder>() {

    private val uniqueAsteroids = asteroids.distinctBy { it.ime.trim().lowercase() }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.asteroid_title)
        val asteroidImg: ImageView = view.findViewById(R.id.asteroid_img)
        val menuIcon: ImageView = view.findViewById(R.id.asteroid_menu_icon)
        val asteroidInfo: TextView = view.findViewById(R.id.asteroidinfo)
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
            "vesta" -> R.drawable.vesta
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
    }
}
