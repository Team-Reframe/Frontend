package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.api.RetrofitClient
import com.example.reframe.api.SignupRequest
import com.example.reframe.api.SignupResponse
import com.example.reframe.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
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
            finish() // 이전 화면으로 돌아감
        }

        binding.btnNext.setOnClickListener {
            if (validateInputs()) {
                // ✅ 모든 입력이 유효하면 API 호출!
                val name = binding.etName.text.toString().trim()
                val email = binding.etId.text.toString().trim()
                val password = binding.etPassword.text.toString()
                val confirmPassword = binding.etPasswordConfirm.text.toString()

                val signupRequest = SignupRequest(email, password, confirmPassword, name)

                RetrofitClient.instance.signUp(signupRequest).enqueue(object : Callback<SignupResponse> {
                    override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@SignupActivity, "회원가입 성공!", Toast.LENGTH_SHORT).show()

                            // ✅ 회원가입 성공 → SignupSuccessActivity로 이동
                            val intent = Intent(this@SignupActivity, SignupSuccessActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@SignupActivity, "회원가입 실패: ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(this@SignupActivity, "에러: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
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
        val email = binding.etId.text.toString().trim()
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
        if (email.isEmpty()) {
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
