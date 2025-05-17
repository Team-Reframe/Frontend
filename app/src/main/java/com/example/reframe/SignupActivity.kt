package com.example.reframe

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
    }
}