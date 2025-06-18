package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // 메뉴 아이템을 위한 뷰 찾기
        val tvMyInfo = findViewById<TextView>(R.id.tv_my_info)
        val tvPointHistory = findViewById<TextView>(R.id.tv_point_history)
        val tvPasswordSecurity = findViewById<TextView>(R.id.tv_password_security)
        val tvLogout = findViewById<TextView>(R.id.tv_logout)
        val tvWithdrawal = findViewById<TextView>(R.id.tv_withdrawal)

        // 다른 아이템들(도움말, 계정 상태 등)을 위한 임시 클릭 리스너
        // 다른 액티비티로 이동한다면 비슷하게 구현할 수 있습니다.
        val tvHelp = findViewById<TextView>(R.id.tv_help)
        val tvAccountStatus = findViewById<TextView>(R.id.tv_account_status)
        val tvReportProblem = findViewById<TextView>(R.id.tv_report_problem)
        val tvCustomerCenter = findViewById<TextView>(R.id.tv_customer_center)

        // 클릭 리스너 설정
        tvMyInfo.setOnClickListener {
            startActivity(Intent(this, MyInfoActivity::class.java))
        }

        tvPointHistory.setOnClickListener {
            startActivity(Intent(this, PointHistoryActivity::class.java))
        }

        tvPasswordSecurity.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }

        tvWithdrawal.setOnClickListener {
            startActivity(Intent(this, WithdrawalActivity::class.java))
        }

        tvLogout.setOnClickListener {
            showLogoutDialog()
        }

        // 예시:
        // tvHelp.setOnClickListener { // HelpActivity로 이동 }

        setupBottomNavigation()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setMessage("로그아웃 하시겠습니까?")
            .setPositiveButton("YES") { dialog, _ ->
                // 여기에 로그아웃 작업 수행 (예: 세션, 토큰 지우기)
                // 로그인 화면으로 이동
                val intent = Intent(this, LoginActivity::class.java)
                // 백 스택(이전 액티비티 기록)을 지워서 로그인 화면으로 돌아간 후 뒤로 가기 버튼을 눌러도 다시 프로필 화면으로 돌아오지 않도록 합니다.
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish() // ProfileActivity 종료
                dialog.dismiss()
            }
            .setNegativeButton("NO") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_profile // 프로필 아이템 강조

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0) // 화면 전환 애니메이션 제거
                    finish() // 현재 액티비티 종료
                    true
                }
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
                    // 이미 프로필 화면이므로 아무것도 안 하거나 새로고침
                    true
                }
                else -> false
            }
        }
    }
}