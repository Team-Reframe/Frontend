package com.example.reframe.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityRewardBinding // 레이아웃 이름은 그대로 사용

class RewardFragment : Fragment() {

    private var _binding: ActivityRewardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ScanViewModel by viewModels()
    private var isBoxClicked = false
    private var purchaseId: Long = -1L

    // Fragment 생성 시 데이터를 전달받기 위한 newInstance 패턴
    companion object {
        private const val ARG_PURCHASE_ID = "purchaseId"

        fun newInstance(purchaseId: Long): RewardFragment {
            val fragment = RewardFragment()
            val args = Bundle()
            args.putLong(ARG_PURCHASE_ID, purchaseId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityRewardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        purchaseId = arguments?.getLong(ARG_PURCHASE_ID, -1L) ?: -1L

        binding.imageBoxClosed.setOnClickListener {
            if (!isBoxClicked && purchaseId != -1L) {
                isBoxClicked = true
                val memberId = SessionManager.getMemberId(requireContext())
                if (memberId != -1L) {
                    viewModel.claimReward(purchaseId, memberId)
                } else {
                    Toast.makeText(requireContext(), "리워드 요청 실패: 사용자 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                    isBoxClicked = false
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.rewardState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> { /* 로딩 UI 처리 */ }
                is UiState.Success -> {
                    showRewardAnimation(state.data.points)
                }
                is UiState.Error -> {
                    isBoxClicked = false
                    Toast.makeText(requireContext(), "리워드 요청 실패: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showRewardAnimation(points: Int) {
        binding.textClickBox.isVisible = false
        binding.imageBoxClosed.isVisible = false
        binding.textRewardMessage.isVisible = true
        binding.textPoints.isVisible = true
        binding.lottieBoxOpen.isVisible = true
        binding.textRewardMessage.text = "${points}p를 받았어요!"
        binding.textPoints.text = "+${points}p"
        binding.lottieBoxOpen.playAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}