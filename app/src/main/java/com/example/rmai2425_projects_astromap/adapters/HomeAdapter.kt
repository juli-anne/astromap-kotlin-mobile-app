package com.example.rmai2425_projects_astromap.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.database.SuncevSustavInfo

class HomeAdapter(private val items: List<SuncevSustavInfo>) :
    RecyclerView.Adapter<HomeAdapter.SunSystemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SunSystemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_home, parent, false)
        return SunSystemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SunSystemViewHolder, position: Int) {
        val item = items[position]

        holder.tvKategorija.text = item.kategorija
        holder.tvKratkiOpis.text = item.kratkiOpis
        holder.tvNaslov.text = item.naslov
        holder.tvDetaljanOpis.text = item.detaljanOpis


        holder.tvNaslov.visibility = View.GONE
        holder.tvDetaljanOpis.visibility = View.GONE

        holder.menuIcon.setOnClickListener {
            val visible = holder.tvDetaljanOpis.visibility == View.VISIBLE
            holder.tvDetaljanOpis.visibility = if (visible) View.GONE else View.VISIBLE
            holder.tvNaslov.visibility = if (visible) View.GONE else View.VISIBLE
        }
    }

    override fun getItemCount(): Int = items.size

    inner class SunSystemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvKategorija: TextView = itemView.findViewById(R.id.tv_kategorija)
        val tvKratkiOpis: TextView = itemView.findViewById(R.id.tv_kratki_opis)
        val tvNaslov: TextView = itemView.findViewById(R.id.tv_naslov)
        val tvDetaljanOpis: TextView = itemView.findViewById(R.id.tv_detaljan_opis)
        val menuIcon: ImageView = itemView.findViewById(R.id.solar_menu_icon)
    }
}
