package com.example.mohammedportfolio.ui.education

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.data.entity.EducationEntity
import com.example.mohammedportfolio.databinding.ItemEducationBinding

class EducationAdapter(
    private var items: List<EducationEntity> = emptyList()
) : RecyclerView.Adapter<EducationAdapter.ViewHolder>() {

    fun updateList(newList: List<EducationEntity>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEducationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: ItemEducationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EducationEntity) {
            binding.tvInstitution.text = item.institution
            binding.tvQualification.text = item.qualification
            binding.tvField.text = item.fieldOfStudy
            binding.tvYears.text = "${item.startYear} — ${item.endYear}"
        }
    }
}