package com.example.mohammedportfolio.ui.admin.manage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.R

data class ManageItem(
    val id: Long,
    val title: String,
    val subtitle: String,
    val rawData: Any
)

class ManageAdapter(
    private var items: List<ManageItem> = emptyList(),
    private val onEdit: (ManageItem) -> Unit,
    private val onDelete: (ManageItem) -> Unit
) : RecyclerView.Adapter<ManageAdapter.ViewHolder>() {

    fun updateList(newList: List<ManageItem>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_manage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvItemTitle)
        private val tvSubtitle: TextView = view.findViewById(R.id.tvItemSubtitle)
        private val btnEdit: View = view.findViewById(R.id.btnEdit)
        private val btnDelete: View = view.findViewById(R.id.btnDelete)

        fun bind(item: ManageItem) {
            tvTitle.text = item.title
            tvSubtitle.text = item.subtitle
            btnEdit.setOnClickListener { onEdit(item) }
            btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}