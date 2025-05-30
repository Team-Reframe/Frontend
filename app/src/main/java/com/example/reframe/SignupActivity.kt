package com.example.reframe

import com.example.reframe.R
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // (이전 TextWatcher 설정들은 그대로 둡니다)
        val passwordTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validatePasswordConfirmation()
            }
        }
        binding.etPassword.addTextChangedListener(passwordTextWatcher)
        binding.etPasswordConfirm.addTextChangedListener(passwordTextWatcher)


        binding.btnBack.setOnClickListener {
            finish() // 현재 액티비티 종료, 이전 화면(LoginActivity)으로 돌아감
        }

        binding.btnNext.setOnClickListener {
            if (validateInputs()) {
                // 모든 입력이 유효하면 회원가입 처리
                // TODO: 실제 회원가입 로직 구현 (예: 서버에 데이터 전송)

                // 회원가입 로직이 성공했다고 가정하고 SignupSuccessActivity로 이동
                Toast.makeText(this, "회원가입 처리 중...", Toast.LENGTH_SHORT).show() // 임시 메시지

                val intent = Intent(this, SignupSuccessActivity::class.java)
                // SignupActivity를 백 스택에서 제거하여 사용자가 SignupSuccessActivity에서 뒤로가기 했을 때
                // 다시 회원가입 화면으로 돌아오지 않도록 합니다.
                // 그리고 LoginActivity까지의 모든 화면을 스택에서 제거할 수도 있습니다.
                // 이는 앱의 흐름에 따라 결정합니다.
                // 여기서는 SignupActivity만 종료하고, SignupSuccessActivity에서 로그인 화면으로 명확히 이동하도록 합니다.
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK // 이렇게 하면 이전 태스크를 다 지움
                // 또는 SignupActivity만 닫고 싶다면:
                // finish() 호출 전에 startActivity(intent)를 하고, SignupSuccessActivity에서 Login으로 갈 때 적절한 플래그 사용

                startActivity(intent)
                finish() // 현재 SignupActivity 종료
            }
        }
    }

    private fun validatePasswordConfirmation() {
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etPasswordConfirm.text.toString()

        if (confirmPassword.isNotEmpty() && password != confirmPassword) {
            binding.tvPasswordConfirmError.visibility = View.VISIBLE
        } else {
            binding.tvPasswordConfirmError.visibility = View.GONE
        }
    }

    private fun isPasswordFormatValid(password: String): Boolean {
        if (password.length < 7) return false
        val hasLetter = password.any { it.isLetter() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        return hasLetter && hasSpecialChar
    }

    private fun validateInputs(): Boolean {
        val name = binding.etName.text.toString().trim()
        val id = binding.etId.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etPasswordConfirm.text.toString()

        binding.tilName.error = null
        binding.tilId.error = null
        binding.tilPassword.error = null
        binding.tilPasswordConfirm.error = null
        binding.tvPasswordConfirmError.visibility = View.GONE

        if (name.isEmpty()) {
            binding.tilName.error = getString(R.string.error_name_empty)
            binding.etName.requestFocus()
            return false
        }
        if (id.isEmpty()) {
            binding.tilId.error = getString(R.string.error_id_empty)
            binding.etId.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            binding.tilPassword.error = getString(R.string.error_password_empty)
            binding.etPassword.requestFocus()
            return false
        }
        if (!isPasswordFormatValid(password)) {
            binding.tilPassword.error = getString(R.string.error_password_invalid)
            binding.etPassword.requestFocus()
            return false
        }
        if (confirmPassword.isEmpty()) {
            binding.tilPasswordConfirm.error = getString(R.string.error_password_confirm_empty)
            binding.etPasswordConfirm.requestFocus()
            return false
        }
        if (password != confirmPassword) {
            binding.tvPasswordConfirmError.visibility = View.VISIBLE
            binding.etPasswordConfirm.requestFocus()
            return false
        }
        return true
    }
}