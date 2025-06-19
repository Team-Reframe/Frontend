package com.example.reframe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reframe.api.StoreResponse
import com.example.reframe.databinding.ItemStoreBinding
import com.bumptech.glide.Glide


class StoreAdapter(
    private val onClick: (StoreResponse) -> Unit
) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    private val stores = mutableListOf<StoreResponse>()

    fun submitList(list: List<StoreResponse>) {
        stores.clear()
        stores.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(stores[position])
    }

    inner class StoreViewHolder(private val binding: ItemStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(store: StoreResponse) {
            binding.tvStoreName.text = store.name
            binding.tvStoreAddress.text = store.address

            // ✅ 이미지 반드시 넣기
            Glide.with(binding.storeImage.context)
                .load(store.imageResId)
                .into(binding.storeImage)

            binding.root.setOnClickListener { onClick(store) }
        }
    }

}
