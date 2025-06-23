package com.example.reframe.ui.profile

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reframe.R
import com.example.reframe.adapters.PointHistoryAdapter
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityPointHistoryBinding
import com.example.reframe.ui.scan.UiState
import java.text.DecimalFormat

class PointHistoryFragment : Fragment() {

    private var _binding: ActivityPointHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var pointHistoryAdapter: PointHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityPointHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
        observeViewModel()

        val memberId = SessionManager.getMemberId(requireContext())
        if (memberId != -1L) {
            viewModel.fetchTotalPoints(memberId)
            viewModel.fetchPointHistory(memberId)
            viewModel.fetchMyInfo(memberId)
        } else {
            Toast.makeText(requireContext(), "로그인 정보가 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        pointHistoryAdapter = PointHistoryAdapter(emptyList())
        binding.rvPointHistory.adapter = pointHistoryAdapter
    }

    private fun observeViewModel() {
        viewModel.totalPoints.observe(viewLifecycleOwner) { state ->
            // ... (기존 코드)
        }
        viewModel.pointHistory.observe(viewLifecycleOwner) { state ->
            // ... (기존 코드)
        }
        viewModel.userInfo.observe(viewLifecycleOwner) { state ->
            if (state is UiState.Success) {
                setStyledTitle(state.data.name)
            }
        }
    }

    private fun setStyledTitle(userName: String) {
        val htmlString = getString(R.string.user_point_title, userName)
        val spannedText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(htmlString)
        }
        binding.tvUserPointTitle.text = spannedText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}