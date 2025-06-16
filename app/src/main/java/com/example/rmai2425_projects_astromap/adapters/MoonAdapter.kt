package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.MoonDetailActivity
import com.example.rmai2425_projects_astromap.database.Mjesec

class MoonAdapter(
    private val moons: List<Mjesec>,
    private val planetIdToNameMap: Map<Int, String>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<MoonAdapter.MyViewHolder>() {

    private val uniqueMoons = moons.distinctBy { it.ime.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.moon_title)
        val moonImg: ImageView = view.findViewById(R.id.moon_img)
        val menuIcon: ImageView = view.findViewById(R.id.moon_menu_icon)
        val moonInfo: TextView = view.findViewById(R.id.mooninfo)
        val diameterIcon: ImageView = view.findViewById(R.id.size_icon)
        val moonDiameter: TextView = view.findViewById(R.id.moon_size)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_moon, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniqueMoons.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val moon = uniqueMoons[position]
        holder.title.text = moon.ime
        holder.moonInfo.text = moon.kratkiOpis
        holder.moonDiameter.text = "${moon.velicina} km"
        val context = holder.itemView.context
        val dummyImage = when (moon.ime.trim().lowercase()) {
            "mjesec" -> R.drawable.earthmoon
            "europa" -> R.drawable.europamoon
            "titan" -> R.drawable.titanmoon
            "ganimed" -> R.drawable.ganimedmoon
            "io" -> R.drawable.iomoon
            "triton" -> R.drawable.tritonmoon
            "miranda" -> R.drawable.mirandamoon
            else -> R.drawable.earthmoon
        }
        holder.moonImg.setImageResource(dummyImage)
        holder.menuIcon.setOnClickListener {
            val planetIme = planetIdToNameMap[moon.planetId] ?: "Nepoznato"
            val intent = Intent(context, MoonDetailActivity::class.java).apply {
                putExtra("ime", moon.ime)
                putExtra("planetIme", planetIme)
                putExtra("detaljanOpis", moon.detaljanOpis)
                putExtra("velicina", moon.velicina)
                putExtra("zanimljivosti", moon.zanimljivosti)
                putExtra("imgRes", dummyImage)
            }
            context.startActivity(intent)
        }

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(moon.ime)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(moon.ime)
                    markModuleCompleted(moon.ime)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniqueMoons.indexOfFirst { it.ime == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}
