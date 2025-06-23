package com.example.reframe.ui.history

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reframe.R
import com.example.reframe.data.SessionManager
import com.example.reframe.data.dto.ReceiptHistoryResponse
import com.example.reframe.data.dto.ReviewRequest
import com.example.reframe.databinding.FragmentReceiptHistoryBinding
import com.example.reframe.ui.reveiw.MyReviewsFragment
import com.example.reframe.ui.scan.UiState

class ReceiptHistoryFragment : Fragment() {

    private var _binding: FragmentReceiptHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReceiptViewModel by viewModels()
    private lateinit var adapter: ReceiptHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReceiptHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberId = SessionManager.getMemberId(requireContext())

        setupRecyclerView()
        setupClickListeners()
        observeViewModel()

        if (memberId != -1L) {
            viewModel.fetchTotalPoints(memberId)
            viewModel.fetchReceiptHistory(memberId)
        }
    }

    private fun setupRecyclerView() {
        adapter = ReceiptHistoryAdapter(
            onWriteReviewClick = { item -> showWriteReviewDialog(item) },
            onShowReviewClick = { item -> Toast.makeText(requireContext(), "리뷰 ID: ${item.purchaseId} 보기", Toast.LENGTH_SHORT).show() }
        )
        binding.rvReceiptHistory.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.btnMyReviews.setOnClickListener {
            (activity as? ReceiptHistoryActivity)?.replaceFragment(MyReviewsFragment())
        }
    }

    private fun observeViewModel() {
        viewModel.totalPoints.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                binding.tvTotalPoints.text = "${state.data.total} P"
            }
        }

        viewModel.receiptHistory.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                adapter.submitList(state.data)
            }
        }

        viewModel.reviewPostState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    Toast.makeText(requireContext(), "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                    val memberId = SessionManager.getMemberId(requireContext())
                    if (memberId != -1L) viewModel.fetchReceiptHistory(memberId)
                }
                is UiState.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    private fun showWriteReviewDialog(item: ReceiptHistoryResponse) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_write_review, null)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)
        val contentEt = dialogView.findViewById<EditText>(R.id.et_review_content)

        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton("등록") { dialog, _ ->
                val memberId = SessionManager.getMemberId(requireContext())
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}