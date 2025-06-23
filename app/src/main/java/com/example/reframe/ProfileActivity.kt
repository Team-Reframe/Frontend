package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityProfileBinding
import com.example.reframe.ui.profile.ProfileViewModel
import com.example.reframe.ui.scan.UiState

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setupBottomNavigation()
        observeViewModel()
    }

    private fun setupClickListeners() {
        // ... (다른 클릭 리스너들)
        binding.tvLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun observeViewModel() {
        viewModel.logoutState.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    // 세션 데이터 클리어 및 로그인 화면으로 이동
                    SessionManager.clearData(this)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                is UiState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setMessage("로그아웃 하시겠습니까?")
            .setPositiveButton("YES") { _, _ ->
                val token = SessionManager.getToken(this)
                if (!token.isNullOrEmpty()) {
                    viewModel.logout(token)
                } else {
                    // 토큰이 없는 경우(비정상)에도 로컬 데이터 지우고 로그인 화면으로
                    SessionManager.clearData(this)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
            .setNegativeButton("NO", null)
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