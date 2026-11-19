package com.example.mohammedportfolio.ui.certificates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.data.entity.CertificateEntity
import com.example.mohammedportfolio.databinding.ItemCertificateBinding

class CertificatesAdapter(
    private var items: List<CertificateEntity> = emptyList()
) : RecyclerView.Adapter<CertificatesAdapter.ViewHolder>() {

    fun updateList(newList: List<CertificateEntity>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCertificateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: ItemCertificateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CertificateEntity) {
            binding.tvCertTitle.text = item.title
            binding.tvCertIssuer.text = item.issuer
            binding.tvCertDate.text = item.date
        }
    }
}