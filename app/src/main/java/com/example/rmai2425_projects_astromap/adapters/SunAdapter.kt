package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.SunDetailActivity
import com.example.rmai2425_projects_astromap.database.Sunce

class SunAdapter(
    private val sunca: List<Sunce>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<SunAdapter.MyViewHolder>() {

    private val uniqueSunca = sunca.distinctBy { it.ime.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.sun_title)
        val sunImg: ImageView = view.findViewById(R.id.sun_img)
        val menuIcon: ImageView = view.findViewById(R.id.sun_menu_icon)
        val sunInfo: TextView = view.findViewById(R.id.suninfo)
        val diameterIcon: ImageView = view.findViewById(R.id.sun_diameter_icon)
        val sunDiameter: TextView = view.findViewById(R.id.sun_diameter)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_sun, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniqueSunca.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sunce = uniqueSunca[position]
        holder.title.text = sunce.ime
        holder.sunInfo.text = sunce.kratkiOpis
        holder.sunDiameter.text = "${sunce.promjer} km"
        val context = holder.itemView.context
        val imageResource = R.drawable.sunce
        holder.sunImg.setImageResource(imageResource)
        holder.menuIcon.setOnClickListener {
            val intent = Intent(context, SunDetailActivity::class.java).apply {
                putExtra("ime", sunce.ime)
                putExtra("detaljanOpis", sunce.detaljanOpis)
                putExtra("povrsinskaTemperatura", sunce.povrsinskaTemperatura)
                putExtra("temperaturaJezgre", sunce.temperaturaJezgre)
                putExtra("promjer", sunce.promjer)
                putExtra("masa", sunce.masa)
                putExtra("sastav", sunce.sastav)
                putExtra("imgRes", imageResource)
            }
            context.startActivity(intent)
        }

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(sunce.ime)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(sunce.ime)
                    markModuleCompleted(sunce.ime)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniqueSunca.indexOfFirst { it.ime == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}