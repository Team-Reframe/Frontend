package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // ✅ LoginActivity에서 넘어온 이름 받기
        val userName = intent.getStringExtra("USER_NAME") ?: "사용자"

        // ✅ 이름 TextView 설정
        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        tvUserName.text = userName

        // ✅ 인사말 TextView 설정
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        tvGreeting.text = "님, 반갑습니다."

        // ✅ BottomNavigationView 설정은 그대로
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_home

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_receipt -> {
                    startActivity(Intent(this, ReceiptActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_scanner -> {
                    startActivity(Intent(this, ScannerActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_map -> {
                    startActivity(Intent(this, MapActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
