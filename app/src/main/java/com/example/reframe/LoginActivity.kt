package com.example.reframe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 여기에 로그인 버튼 클릭 이벤트 등을 추가하면 됩니다
    }
}
