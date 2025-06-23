package com.example.reframe.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityProfileBinding
import com.example.reframe.ui.history.ReceiptHistoryFragment
import com.example.reframe.ui.profile.ProfileMenuFragment
import com.example.reframe.ui.profile.ProfileViewModel
import com.example.reframe.ui.scan.ScanActivity
import com.example.reframe.ui.scan.UiState

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_profile_container, ProfileMenuFragment())
                .commit()
        }

        setupBottomNavigation()
        observeViewModel()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_profile_container, fragment)
            .addToBackStack(null) // 뒤로가기 지원
            .commit()
    }

    fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setMessage("로그아웃 하시겠습니까?")
            .setPositiveButton("YES") { _, _ ->
                val token = SessionManager.getToken(this)
                if (!token.isNullOrEmpty()) {
                    viewModel.logout(token)
                } else {
                    SessionManager.clearData(this)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            .setNegativeButton("NO", null)
            .show()
    }

    private fun observeViewModel() {
        viewModel.logoutState.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
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

    private fun setupBottomNavigation() {
        val bottomNavigation = binding.bottomNavigation
        bottomNavigation.selectedItemId = R.id.nav_profile

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navigateTo(HomeActivity::class.java)
                R.id.nav_receipt -> navigateTo(ReceiptHistoryFragment::class.java)
                R.id.nav_scan -> navigateTo(ScanActivity::class.java)
                R.id.nav_map -> navigateTo(MapActivity::class.java)
                R.id.nav_profile -> {
                    // 첫 화면(메뉴)으로 돌아가기
                    supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_profile_container, ProfileMenuFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateTo(activityClass: Class<*>): Boolean {
        startActivity(Intent(this, activityClass))
        overridePendingTransition(0, 0)
        finish()
        return true
    }
}