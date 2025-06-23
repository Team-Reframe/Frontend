package com.example.reframe.ui.reveiw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reframe.data.dto.MyReviewResponse
import com.example.reframe.databinding.ItemMyReviewBinding

class MyReviewsAdapter(
    private val onEditClick: (MyReviewResponse) -> Unit,
    private val onDeleteClick: (MyReviewResponse) -> Unit
) : ListAdapter<MyReviewResponse, MyReviewsAdapter.MyReviewViewHolder>(MyReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewViewHolder {
        val binding = ItemMyReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyReviewViewHolder(private val binding: ItemMyReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: MyReviewResponse) {
            binding.tvStoreName.text = review.storeName
            binding.tvDate.text = review.createdAt
            binding.ratingBarDisplay.rating = review.rating
            binding.tvReviewContent.text = review.content
            binding.btnEdit.setOnClickListener { onEditClick(review) }
            binding.btnDelete.setOnClickListener { onDeleteClick(review) }
        }
    }
}

class MyReviewDiffCallback : DiffUtil.ItemCallback<MyReviewResponse>() {
    override fun areItemsTheSame(oldItem: MyReviewResponse, newItem: MyReviewResponse): Boolean {
        return oldItem.reviewId == newItem.reviewId
    }
    override fun areContentsTheSame(oldItem: MyReviewResponse, newItem: MyReviewResponse): Boolean {
        return oldItem == newItem
    }
}