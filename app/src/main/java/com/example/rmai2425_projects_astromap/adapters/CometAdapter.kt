package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.CometDetailActivity
import com.example.rmai2425_projects_astromap.database.Komet

class CometAdapter(
    private val kometi: List<Komet>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<CometAdapter.MyViewHolder>() {

    private val uniqueKometi = kometi.distinctBy { it.ime.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.comet_title)
        val cometImg: ImageView = view.findViewById(R.id.comet_img)
        val menuIcon: ImageView = view.findViewById(R.id.comet_menu_icon)
        val cometInfo: TextView = view.findViewById(R.id.cometinfo)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_comet, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniqueKometi.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val komet = uniqueKometi[position]
        holder.title.text = komet.ime
        holder.cometInfo.text = komet.kratkiOpis
        val context = holder.itemView.context
        val dummyImage = when (komet.ime.trim().lowercase()) {
            "halleyjev komet" -> R.drawable.halle
            "hale-bopp" -> R.drawable.halebopp
            "neowise" -> R.drawable.neowise
            "tempel 1" -> R.drawable.tempel1
            "enckeov komet" -> R.drawable.encke
            else -> R.drawable.halle
        }
        holder.cometImg.setImageResource(dummyImage)
        holder.menuIcon.setOnClickListener {
            val intent = Intent(context, CometDetailActivity::class.java).apply {
                putExtra("ime", komet.ime)
                putExtra("kratkiOpis", komet.kratkiOpis)
                putExtra("orbitalniPeriod", komet.orbitalniPeriod)
                putExtra("posljednjiPerihel", komet.posljednjiPerihel)
                putExtra("sljedeciPerihel", komet.sljedeciPerihel)
                putExtra("velicinaJezgre", komet.velicinaJezgre)
                putExtra("imgRes", dummyImage)
            }
            context.startActivity(intent)
        }

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(komet.ime)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(komet.ime)
                    markModuleCompleted(komet.ime)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniqueKometi.indexOfFirst { it.ime == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}