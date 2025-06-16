package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.ObjectDetailActivity
import com.example.rmai2425_projects_astromap.database.ObjektSuncevogSustava

class ObjectAdapter(
    private val objekti: List<ObjektSuncevogSustava>,
    private val isUserLoggedIn: Boolean,
    completedModules: Set<String>,
    private val onModuleComplete: (String) -> Unit
) : RecyclerView.Adapter<ObjectAdapter.MyViewHolder>() {

    private val uniqueObjects = objekti.distinctBy { it.ime.trim().lowercase() }
    private val completedModules: MutableSet<String> = completedModules.toMutableSet()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.object_title)
        val objectImg: ImageView = view.findViewById(R.id.object_img)
        val menuIcon: ImageView = view.findViewById(R.id.object_menu_icon)
        val objectInfo: TextView = view.findViewById(R.id.objectinfo)
        val completionButton: TextView = view.findViewById(R.id.completion_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_object, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = uniqueObjects.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val objekt = uniqueObjects[position]
        val context = holder.itemView.context
        holder.title.text = objekt.ime
        holder.objectInfo.text = objekt.opis
        val imageRes = when (objekt.ime.trim().lowercase()) {
            "pluton" -> R.drawable.pluton
            "ceres" -> R.drawable.ceres
            "meteoroid" -> R.drawable.meteoroid
            "meteor" -> R.drawable.meteor
            "meteorit" -> R.drawable.meteorite
            "svemirska prašina" -> R.drawable.space_dust
            else -> R.drawable.pluton
        }
        holder.objectImg.setImageResource(imageRes)
        holder.menuIcon.setOnClickListener {
            val intent = Intent(context, ObjectDetailActivity::class.java).apply {
                putExtra("ime", objekt.ime)
                putExtra("tip", objekt.tip)
                putExtra("smjestaj", objekt.smjestaj)
                putExtra("opis", objekt.opis)
                putExtra("zanimljivosti", objekt.zanimljivosti)
                putExtra("velicina", objekt.velicina)
                putExtra("sastav", objekt.sastav)
                putExtra("imgRes", imageRes)
            }
            context.startActivity(intent)
        }

        if (isUserLoggedIn) {
            holder.completionButton.visibility = View.VISIBLE
            if (completedModules.contains(objekt.ime)) {
                holder.completionButton.text = "Dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.success_green))
                holder.completionButton.isEnabled = false
            } else {
                holder.completionButton.text = "Označi kao dovršeno"
                holder.completionButton.setTextColor(context.getColor(R.color.white))
                holder.completionButton.isEnabled = true
                holder.completionButton.setOnClickListener {
                    onModuleComplete(objekt.ime)
                    markModuleCompleted(objekt.ime)
                }
            }
        } else {
            holder.completionButton.visibility = View.GONE
        }
    }

    fun markModuleCompleted(moduleName: String) {
        val index = uniqueObjects.indexOfFirst { it.ime == moduleName }
        if (index != -1) {
            completedModules.add(moduleName)
            notifyItemChanged(index)
        }
    }
}
