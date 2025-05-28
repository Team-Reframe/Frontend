package com.example.reframe

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

        // 비밀번호 확인 일치 여부 체크
        binding.editTextPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val pw = binding.editTextPassword.text.toString()
                val pwCheck = s.toString()
                binding.passwordCheckError.visibility =
                    if (pwCheck.isNotEmpty() && pw != pwCheck) View.VISIBLE else View.GONE
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener { finish() }

        // 다음 버튼
        binding.btnNext.setOnClickListener {
            if (validateInputs()) {
                startActivity(Intent(this, SignupSuccessActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "입력값을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val name = binding.editTextName.text.toString().trim()
        val id = binding.editTextId.text.toString().trim()
        val pw = binding.editTextPassword.text.toString().trim()
        val pwCheck = binding.editTextPasswordCheck.text.toString().trim()
        if (name.isEmpty() || id.isEmpty() || pw.isEmpty() || pwCheck.isEmpty()) return false
        if (pw != pwCheck) return false
        // 비밀번호: 영어, 특수문자 포함 7자리 이상
        val pattern = Regex("^(?=.*[A-Za-z])(?=.*[!@#\$%^&*()_+])[A-Za-z\\d!@#\$%^&*()_+]{7,}$")
        return pattern.matches(pw)
    }
}
