package com.example.reframe.ui.receipt

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.R
import com.example.reframe.data.SessionManager
import com.example.reframe.data.dto.ReceiptHistoryResponse
import com.example.reframe.data.dto.ReviewRequest
import com.example.reframe.databinding.ActivityReceiptHistoryBinding
import com.example.reframe.ui.scan.UiState
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup

class ReceiptHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiptHistoryBinding
    private val viewModel: ReceiptViewModel by viewModels()
    private lateinit var adapter: ReceiptHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memberId = SessionManager.getMemberId(this)

        setupRecyclerView()
        setupClickListeners()
        observeViewModel()

        // 데이터 로드
        if (memberId != -1L) {
            viewModel.fetchTotalPoints(memberId)
            viewModel.fetchReceiptHistory(memberId)
        }
    }

    private fun setupRecyclerView() {
        adapter = ReceiptHistoryAdapter(
            onWriteReviewClick = { item -> showWriteReviewDialog(item) },
            onShowReviewClick = { item ->
                Toast.makeText(this, "리뷰 ID: ${item.purchaseId} 보기", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvReceiptHistory.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.btnMyReviews.setOnClickListener {
            startActivity(Intent(this, MyReviewsActivity::class.java))
        }
    }

    private fun observeViewModel() {
        viewModel.totalPoints.observe(this) { state ->
            if (state is UiState.Success) {
                binding.tvTotalPoints.text = "${state.data.total} P"
            }
        }

        viewModel.receiptHistory.observe(this) { state ->
            if (state is UiState.Success) {
                adapter.submitList(state.data)
            }
        }

        viewModel.reviewPostState.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    Toast.makeText(this, "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                    // 목록 새로고침
                    val memberId = SessionManager.getMemberId(this)
                    if (memberId != -1L) viewModel.fetchReceiptHistory(memberId)
                }

                is UiState.Error -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    private fun showWriteReviewDialog(item: ReceiptHistoryResponse) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_write_review, null)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)
        val contentEt = dialogView.findViewById<EditText>(R.id.et_review_content)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("등록") { dialog, _ ->
                val memberId = SessionManager.getMemberId(this)
                val request = ReviewRequest(
                    memberId = memberId,
                    purchaseId = item.purchaseId,
                    storeId = item.storeId,
                    content = contentEt.text.toString(),
                    rating = ratingBar.rating
                )
                viewModel.postReview(request)
                dialog.dismiss()
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.CENTER)

            val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            setLayout(width, height)
        }
    }
}