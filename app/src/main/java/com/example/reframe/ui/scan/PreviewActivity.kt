package com.example.reframe.ui.scan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding
    private val viewModel: ScanViewModel by viewModels()
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ScanActivity에서 전달한 이미지 URI 받기
        intent.getStringExtra("imageUri")?.let {
            imageUri = Uri.parse(it)
            binding.previewImageView.load(imageUri)
        }

        // '다시 찍기' 버튼: 현재 Activity를 종료하면 이전 화면(ScanActivity)으로 돌아감
        binding.retakeButton.setOnClickListener {
            finish()
        }

        binding.registerButton.setOnClickListener {
            val memberId = SessionManager.getMemberId(this)
            if (memberId != -1L && imageUri != null) {
                viewModel.uploadReceipt(this, memberId, imageUri!!)
            } else {
                Toast.makeText(this, "사용자 정보 또는 이미지가 유효하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uploadState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.registerButton.isEnabled = false
                }
                is UiState.Success -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, "업로드 성공!", Toast.LENGTH_SHORT).show()
                    val purchaseId = state.data.purchaseId // API 응답에 따라 수정

                    // RewardActivity로 purchaseId를 전달하며 이동
                    val intent = Intent(this, RewardActivity::class.java).apply {
                        putExtra("purchaseId", purchaseId)
                    }
                    startActivity(intent)
                    finish() // 현재 PreviewActivity 종료
                }
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.registerButton.isEnabled = true
                    Toast.makeText(this, "업로드 실패: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}