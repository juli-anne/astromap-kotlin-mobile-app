package com.example.rmai2425_projects_astromap.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.activities.QuizActivity
import com.example.rmai2425_projects_astromap.utils.QuizCategory
import com.example.rmai2425_projects_astromap.R

class QuizCategoryAdapter(private val categories: List<QuizCategory>) :
    RecyclerView.Adapter<QuizCategoryAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.quiz_title)
        val quizImg: ImageView = view.findViewById(R.id.quiz_img)
        val menuIcon: ImageView = view.findViewById(R.id.quiz_menu_icon)
        val quizInfo: TextView = view.findViewById(R.id.quiz_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_quiz_category, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categories[position]
        holder.title.text = category.title
        holder.quizInfo.text = category.description
        holder.quizImg.setImageResource(category.imageResource)

        holder.menuIcon.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, QuizActivity::class.java).apply {
                putExtra("category", category.title)
                android.util.Log.d("QuizAdapter", "Starting quiz for category: ${category.title}")
            }
            context.startActivity(intent)
        }
    }
}