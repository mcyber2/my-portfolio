package com.example.mohammedportfolio.ui.teachers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.data.entity.FavoriteTeacherEntity

class TeachersAdapter(
    private var items: List<FavoriteTeacherEntity> = emptyList()
) : RecyclerView.Adapter<TeachersAdapter.ViewHolder>() {

    fun updateList(newList: List<FavoriteTeacherEntity>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teacher, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTeacherName: TextView = view.findViewById(R.id.tvTeacherName)
        private val tvTeacherSubject: TextView = view.findViewById(R.id.tvTeacherSubject)
        private val tvTeacherReason: TextView = view.findViewById(R.id.tvTeacherReason)

        fun bind(item: FavoriteTeacherEntity) {
            tvTeacherName.text = item.name
            tvTeacherSubject.text = item.subject
            tvTeacherReason.text = item.reason
        }
    }
}