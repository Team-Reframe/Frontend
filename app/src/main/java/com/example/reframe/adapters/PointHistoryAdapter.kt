package com.example.reframe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reframe.data.PointHistoryItem
import com.example.reframe.databinding.ListItemPointHistoryBinding

class PointHistoryAdapter(private var items: List<PointHistoryItem>) :
    RecyclerView.Adapter<PointHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemPointHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<PointHistoryItem>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListItemPointHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PointHistoryItem) {
            binding.tvStoreName.text = item.storeName
            binding.tvDate.text = item.date
           binding.tvPoints.text = item.points
        }
    }
}