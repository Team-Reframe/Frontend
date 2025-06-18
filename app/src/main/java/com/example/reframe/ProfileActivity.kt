package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()

        setupBottomNavigation()
    }

    private fun setupClickListeners() {
        // binding 객체에서 직접 ID로 뷰에 접근합니다.
        binding.tvMyInfo.setOnClickListener {
            startActivity(Intent(this, MyInfoActivity::class.java))
        }
        binding.tvPointHistory.setOnClickListener {
            startActivity(Intent(this, PointHistoryActivity::class.java))
        }
        binding.tvPasswordChange.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }
        binding.tvWithdrawal.setOnClickListener {
            startActivity(Intent(this, WithdrawalActivity::class.java))
        }
        binding.tvLogout.setOnClickListener {
            showLogoutDialog()
        }

        // 다른 메뉴 항목들도 여기에 추가 가능
        // binding.tvHelp.setOnClickListener { ... }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setMessage("로그아웃 하시겠습니까?")
            // "YES" 버튼 (긍정)
            .setPositiveButton("YES") { dialog, _ ->
                // 실제 로그아웃 처리 로직 (예: 저장된 토큰 삭제)
                // ...

                // 로그인 화면으로 이동
                val intent = Intent(this, LoginActivity::class.java)
                // 이전 액티비티 기록을 모두 지워서, 로그인 화면에서 뒤로가기 버튼을 눌러도 프로필로 돌아오지 않게 함
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish() // 현재 ProfileActivity를 완전히 종료
            }
            // "NO" 버튼 (부정)
            .setNegativeButton("NO") { dialog, _ ->
                // 아무것도 하지 않고 다이얼로그만 닫음
                dialog.dismiss()
            }
            .show()
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = binding.bottomNavigation
        bottomNavigation.selectedItemId = R.id.nav_profile // 프로필 아이템을 선택된 상태로 표시

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navigateTo(HomeActivity::class.java)
                R.id.nav_receipt -> navigateTo(ReceiptActivity::class.java) // 메뉴 ID가 nav_receipt라고 가정
                R.id.nav_scanner -> navigateTo(ScannerActivity::class.java) // 메뉴 ID가 nav_scanner라고 가정
                R.id.nav_map -> navigateTo(MapActivity::class.java) // 메뉴 ID가 nav_map이라고 가정
                R.id.nav_profile -> {
                    // 이미 현재 화면이므로 아무것도 하지 않음
                    true
                }
                else -> false
            }
        }
    }
    // 중복 코드를 줄이기 위한 함수
    private fun navigateTo(activityClass: Class<*>): Boolean {
        startActivity(Intent(this, activityClass))
        overridePendingTransition(0, 0) // 화면 전환 애니메이션 제거
        finish() // 현재 액티비티 종료
        return true
    }
}