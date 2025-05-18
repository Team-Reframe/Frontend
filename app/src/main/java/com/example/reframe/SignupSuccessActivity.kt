package com.example.reframe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivitySignupSuccessBinding

class SignupSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시작하기 버튼 → HomeActivity 이동
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
