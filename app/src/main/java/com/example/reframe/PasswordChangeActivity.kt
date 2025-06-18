package com.example.reframe

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivityPasswordChangeBinding

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun setupListeners() {
        binding.btnChangePassword.setOnClickListener {
            validateAndChangePassword()
        }
    }

    private fun validateAndChangePassword() {
        val currentPassword = binding.etCurrentPassword.text.toString()
        val newPassword = binding.etNewPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        // 입력 필드 초기화
        binding.tilCurrentPassword.error = null
        binding.tilNewPassword.error = null
        binding.tilConfirmPassword.error = null

        if (currentPassword.isEmpty()) {
            binding.tilCurrentPassword.error = "현재 비밀번호를 입력해주세요."
            return
        }

        // 새 비밀번호 유효성 검사 (7자리 이상)
        if (newPassword.length < 7) {
            binding.tilNewPassword.error = "비밀번호는 7자리 이상이어야 합니다."
            return
        }

        if (newPassword != confirmPassword) {
            binding.tilConfirmPassword.error = "새 비밀번호가 일치하지 않습니다."
            return
        }

        // 실제 비밀번호 변경 로직 구현 (서버 API 호출 등)
        // 1. 현재 비밀번호가 올바른지 서버에 확인
        // 2. 새 비밀번호로 변경 요청

        Toast.makeText(this, "비밀번호 변경이 요청되었습니다.", Toast.LENGTH_SHORT).show()
        // 성공 시 액티비티 종료
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // 뒤로가기 버튼 클릭 시 액티비티 종료
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
