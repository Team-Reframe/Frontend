package com.example.reframe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupTitle.text = "회원가입 화면입니다"

        // 완료 버튼 클릭 시 SignupSuccessActivity로 이동
        binding.btnSignupComplete.setOnClickListener {
            val intent = Intent(this, SignupSuccessActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
