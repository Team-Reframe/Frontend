package com.example.reframe.ui.scan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.reframe.HomeActivity
import com.example.reframe.MapActivity
import com.example.reframe.ProfileActivity
import com.example.reframe.R
import com.example.reframe.databinding.ActivityScanBinding
import com.example.reframe.ui.history.ReceiptHistoryFragment

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.scan_fragment_container, CameraFragment())
                .commit()
        }
        setupBottomNavigation()
    }

    fun navigateToPreview(uri: Uri) {
        binding.bottomNavigation.isVisible = false
        replaceFragment(PreviewFragment.newInstance(uri), true)
    }

    fun navigateToReward(purchaseId: Long) {
        binding.bottomNavigation.isVisible = true
        // PreviewFragment를 스택에서 제거
        supportFragmentManager.popBackStack()
        replaceFragment(RewardFragment.newInstance(purchaseId), true)
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.scan_fragment_container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            binding.bottomNavigation.isVisible = true
        } else {
            super.onBackPressed()
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.nav_scan

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navigateTo(HomeActivity::class.java)
                R.id.nav_receipt -> navigateTo(ReceiptHistoryFragment::class.java)
                R.id.nav_map -> navigateTo(MapActivity::class.java)
                R.id.nav_profile -> navigateTo(ProfileActivity::class.java)
                R.id.nav_scan -> {
                    binding.bottomNavigation.isVisible = true
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    replaceFragment(CameraFragment(), false)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateTo(activityClass: Class<*>): Boolean {
        if (this.javaClass == activityClass) return true
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        overridePendingTransition(0, 0)
        return true
    }
}