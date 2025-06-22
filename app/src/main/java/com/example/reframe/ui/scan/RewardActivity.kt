package com.example.reframe.ui.scan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityRewardBinding

class RewardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRewardBinding
    private val viewModel: ScanViewModel by viewModels()
    private var isBoxClicked = false
    private var purchaseId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // PreviewActivity에서 전달한 purchaseId 받기
        purchaseId = intent.getLongExtra("purchaseId", -1L)

        binding.imageBoxClosed.setOnClickListener {
            if (!isBoxClicked && purchaseId != -1L) {
                isBoxClicked = true
                val memberId = SessionManager.getMemberId(this)
                if (memberId != -1L) {
                    viewModel.claimReward(purchaseId, memberId)
                } else {
                    Toast.makeText(this, "리워드 요청 실패: 사용자 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                    isBoxClicked = false
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.rewardState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> { /* 로딩 UI 처리 */ }
                is UiState.Success -> {
                    showRewardAnimation(state.data.points)
                }
                is UiState.Error -> {
                    isBoxClicked = false // 클릭 다시 활성화
                    Toast.makeText(this, "리워드 요청 실패: ${state.message}", Toast.LENGTH_LONG).show()
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
}