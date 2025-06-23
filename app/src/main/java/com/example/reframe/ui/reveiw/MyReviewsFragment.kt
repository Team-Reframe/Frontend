package com.example.reframe.ui.reveiw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reframe.data.SessionManager
import com.example.reframe.data.dto.MyReviewResponse
import com.example.reframe.databinding.ActivityMyReviewsBinding
import com.example.reframe.ui.history.ReceiptViewModel
import com.example.reframe.ui.scan.UiState

class MyReviewsFragment : Fragment() {

    private var _binding: ActivityMyReviewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReceiptViewModel by viewModels()
    private lateinit var adapter: MyReviewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityMyReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        adapter = MyReviewsAdapter(
            onEditClick = { review ->
                Toast.makeText(requireContext(), "수정: ${review.reviewId}", Toast.LENGTH_SHORT).show()
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
        viewModel.reviewDeleteState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UiState.Success -> {
                    Toast.makeText(requireContext(), "리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }
                is UiState.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    private fun showDeleteConfirmDialog(reviewId: Long) {
        AlertDialog.Builder(requireContext())
            .setTitle("리뷰 삭제")
            .setMessage("정말로 이 리뷰를 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                val memberId = SessionManager.getMemberId(requireContext())
                if (memberId != -1L) {
                    viewModel.deleteReview(reviewId, memberId)
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}