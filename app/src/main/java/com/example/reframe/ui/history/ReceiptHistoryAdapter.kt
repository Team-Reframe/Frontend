package com.example.reframe.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reframe.data.dto.ReceiptHistoryResponse
import com.example.reframe.databinding.ItemReceiptHistoryBinding

class ReceiptHistoryAdapter(
    private val onWriteReviewClick: (ReceiptHistoryResponse) -> Unit,
    private val onShowReviewClick: (ReceiptHistoryResponse) -> Unit
) : ListAdapter<ReceiptHistoryResponse, ReceiptHistoryAdapter.ReceiptViewHolder>(ReceiptDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding = ItemReceiptHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReceiptViewHolder(private val binding: ItemReceiptHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReceiptHistoryResponse) {
            binding.tvDate.text = item.date
            binding.tvStoreName.text = item.storeName
            binding.tvPoints.text = "${item.points}P"
            // binding.ivStoreLogo.load(item.storeLogoUrl)

            if (item.hasReview) {
                binding.btnWriteReview.text = "작성한 리뷰 보기"
                binding.btnWriteReview.setOnClickListener { onShowReviewClick(item) }
            } else {
                binding.btnWriteReview.text = "리뷰 작성하기"
                binding.btnWriteReview.setOnClickListener { onWriteReviewClick(item) }
            }
        }
    }
}

class ReceiptDiffCallback : DiffUtil.ItemCallback<ReceiptHistoryResponse>() {
    override fun areItemsTheSame(oldItem: ReceiptHistoryResponse, newItem: ReceiptHistoryResponse): Boolean {
        return oldItem.purchaseId == newItem.purchaseId
    }
    override fun areContentsTheSame(oldItem: ReceiptHistoryResponse, newItem: ReceiptHistoryResponse): Boolean {
        return oldItem == newItem
    }
}