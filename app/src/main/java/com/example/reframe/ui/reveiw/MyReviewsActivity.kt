package com.example.reframe.ui.receipt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.data.SessionManager
import com.example.reframe.data.dto.MyReviewResponse
import com.example.reframe.databinding.ActivityMyReviewsBinding
import com.example.reframe.ui.scan.UiState
import java.text.SimpleDateFormat
import java.util.*

class MyReviewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyReviewsBinding
    private val viewModel: ReceiptViewModel by viewModels()
    private lateinit var adapter: MyReviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        adapter = MyReviewsAdapter(
            onEditClick = { review ->
                Toast.makeText(this, "수정: ${review.reviewId}", Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { review ->
                showDeleteConfirmDialog(review.reviewId)
            }
        )
        binding.rvMyReviews.adapter = adapter
    }

    private fun loadDummyData() {
        val baseReviews = listOf(
            MyReviewResponse(
                reviewId = 1L, storeName = "GS25 한국공학대점", rating = 5.0f,
                content = "최고입니다!", createdAt = "2024-05-20", purchaseId = 101L, storeId = 201L
            ),
            MyReviewResponse(
                reviewId = 2L, storeName = "스타벅스 시화로데오점", rating = 4.0f,
                content = "사람이 너무 많아서 오래걸려요", createdAt = "2024-05-19", purchaseId = 102L, storeId = 202L
            ),
            MyReviewResponse(
                reviewId = 3L, storeName = "올리브영 시화점", rating = 5.0f,
                content = "좋습니다", createdAt = "2024-05-18", purchaseId = 103L, storeId = 203L
            ),
            MyReviewResponse(
                reviewId = 4L, storeName = "설빙 경기시흥정왕점", rating = 4.0f,
                content = "맛있습니다.", createdAt = "2024-05-17", purchaseId = 104L, storeId = 204L
            )
        )
        val dummyData = mutableListOf<MyReviewResponse>()
        dummyData.addAll(baseReviews)
        dummyData.addAll(baseReviews)

        binding.tvReviewCount.text = "내가 쓴 총 리뷰 ${dummyData.size}개"
        adapter.submitList(dummyData)
    }

    private fun observeViewModel() {

        viewModel.reviewDeleteState.observe(this) { state ->
            when(state) {
                is UiState.Success -> {
                    Toast.makeText(this, "리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }
                is UiState.Error -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    private fun showDeleteConfirmDialog(reviewId: Long) {
        AlertDialog.Builder(this)
            .setTitle("리뷰 삭제")
            .setMessage("정말로 이 리뷰를 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                val memberId = SessionManager.getMemberId(this)
                if (memberId != -1L) {
                    viewModel.deleteReview(reviewId, memberId)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }
}