package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.ConstellationDetailActivity
import com.example.rmai2425_projects_astromap.database.Zvijezdje

class ConstellationAdapter(
    private val constellations: List<Zvijezdje>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<ConstellationAdapter.MyViewHolder>() {

    private val uniqueConstellations = constellations.distinctBy { it.imeHr.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.constellation_title)
        val constellationImg: ImageView = view.findViewById(R.id.constellation_img)
        val menuIcon: ImageView = view.findViewById(R.id.constellation_menu_icon)
        val constellationInfo: TextView = view.findViewById(R.id.constellationinfo)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_constellation, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniqueConstellations.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val constellation = uniqueConstellations[position]
        val context = holder.itemView.context
        holder.title.text = constellation.imeHr
        holder.constellationInfo.text = constellation.pozicija
        val dummyImage = when (constellation.imeHr.trim().lowercase()) {
            "lovac" -> R.drawable.orion
            "veliki medvjed" -> R.drawable.ursa_major
            "maleni medvjed" -> R.drawable.ursa_minor
            "kasiopeja" -> R.drawable.cassiopeia
            "lav" -> R.drawable.leo
            "vaga" -> R.drawable.libra
            "škorpion" -> R.drawable.scorpius
            "strijelac" -> R.drawable.sagittarius
            "kentaur" -> R.drawable.centaurus
            "pješčani sat" -> R.drawable.hourglass
            else -> R.drawable.orion
        }
        holder.constellationImg.setImageResource(dummyImage)
        holder.menuIcon.setOnClickListener {
            val intent = Intent(context, ConstellationDetailActivity::class.java).apply {
                putExtra("ime", constellation.imeHr)
                putExtra("imeLat", constellation.imeLat)
                putExtra("pozicija", constellation.pozicija)
                putExtra("znacaj", constellation.znacaj)
                putExtra("svjetleZvijezde", constellation.svjetleZvijezde)
                putExtra("imgRes", dummyImage)
            }
            context.startActivity(intent)
        }

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(constellation.imeHr)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(constellation.imeHr)
                    markModuleCompleted(constellation.imeHr)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniqueConstellations.indexOfFirst { it.imeHr == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}
