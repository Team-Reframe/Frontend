package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 설정
        binding.logoImage.translationY = 0f
        binding.noticeImage.alpha = 0f
        binding.buttonContainer.alpha = 0f
        binding.receiptImage.visibility = View.INVISIBLE
        binding.receiptImage.scaleY = 0f
        binding.receiptImage.alpha = 0f

        // 버튼 클릭 시 화면 이동
        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.buttonSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        Handler(Looper.getMainLooper()).postDelayed({

            // 공지 + 버튼 fade-in
            binding.noticeImage.animate()
                .alpha(1f)
                .setDuration(400)
                .setInterpolator(DecelerateInterpolator())
                .start()

            binding.buttonContainer.animate()
                .alpha(1f)
                .setDuration(400)
                .setInterpolator(DecelerateInterpolator())
                .start()

            // receipt 등장
            binding.receiptImage.visibility = View.VISIBLE
            binding.receiptImage.animate()
                .scaleY(1f)
                .alpha(1f)
                .setDuration(800)
                .setInterpolator(DecelerateInterpolator())
                .start()

            // 로고 아래로 이동
            val offsetY = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 80f, resources.displayMetrics
            )

            binding.logoImage.animate()
                .translationY(offsetY)
                .setDuration(800)
                .setInterpolator(DecelerateInterpolator())
                .start()

        }, 1500)
    }
}
