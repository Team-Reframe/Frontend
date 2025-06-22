package com.example.reframe.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.reframe.databinding.FragmentRewardBinding

class RewardFragment : Fragment() {

    private var _binding: FragmentRewardBinding? = null
    private val binding get() = _binding!!

    private val args: RewardFragmentArgs by navArgs()
    private val viewModel: ScanViewModel by viewModels()

    private var isBoxClicked = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRewardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageBoxClosed.setOnClickListener {
            if (!isBoxClicked) {
                isBoxClicked = true
                // 실제 로그인된 사용자 ID를 가져오기.
                val memberId = 1L
                viewModel.claimReward(args.purchaseId, memberId)
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.rewardState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // 필요하면 로딩 인디케이터 표시
                }
                is UiState.Success -> {
                    showRewardAnimation(state.data.points)
                }
                is UiState.Error -> {
                    isBoxClicked = false // 다시 클릭할 수 있도록
                    Toast.makeText(context, "리워드 요청 실패: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showRewardAnimation(points: Int) {
        // 1. 클릭 전 UI 숨기기
        binding.textClickBox.isVisible = false
        binding.imageBoxClosed.isVisible = false

        // 2. 클릭 후 UI 보이기
        binding.textRewardMessage.isVisible = true
        binding.textPoints.isVisible = true
        binding.lottieBoxOpen.isVisible = true

        // 3. 텍스트 설정 및 애니메이션 재생
        binding.textRewardMessage.text = "${points}p를 받았어요!"
        binding.textPoints.text = "+${points}p"
        binding.lottieBoxOpen.playAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}