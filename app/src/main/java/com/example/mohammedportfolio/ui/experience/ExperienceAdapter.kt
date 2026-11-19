package com.example.mohammedportfolio.ui.experience

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.data.entity.WorkExperienceEntity
import com.example.mohammedportfolio.databinding.ItemExperienceBinding

class ExperienceAdapter(
    private var items: List<WorkExperienceEntity> = emptyList()
) : RecyclerView.Adapter<ExperienceAdapter.ViewHolder>() {

    fun updateList(newList: List<WorkExperienceEntity>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExperienceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: ItemExperienceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WorkExperienceEntity) {
            binding.tvExpPosition.text = item.position
            binding.tvExpOrganization.text = item.organization
            binding.tvExpDates.text = "${item.startDate} — ${item.endDate}"
            binding.tvExpResponsibilities.text = item.responsibilities
        }
    }
}