package com.example.mohammedportfolio.ui.projects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.data.entity.ProjectEntity
import com.example.mohammedportfolio.databinding.ItemProjectBinding

class ProjectAdapter(
    private var projects: List<ProjectEntity> = emptyList()
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    fun updateList(newList: List<ProjectEntity>) {
        projects = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ItemProjectBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount(): Int = projects.size

    class ProjectViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(project: ProjectEntity) {
            binding.tvProjectTitle.text = project.title
            binding.tvProjectDesc.text = project.description
            binding.tvProjectTech.text = project.technologies
            binding.tvProjectStatus.text = project.status
            binding.tvProjectDate.text = project.date
        }
    }
}