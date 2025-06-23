package com.example.reframe.ui.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.reframe.HomeActivity
import com.example.reframe.MapActivity
import com.example.reframe.ui.profile.ProfileActivity
import com.example.reframe.R
import com.example.reframe.databinding.ActivityReceiptHistoryBinding
import com.example.reframe.ui.reveiw.MyReviewsFragment
import com.example.reframe.ui.scan.ScanActivity

class ReceiptHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiptHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.history_fragment_container, ReceiptHistoryFragment())
                .commit()
        }
        setupBottomNavigation()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.history_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.nav_receipt
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navigateTo(HomeActivity::class.java)
                R.id.nav_scan -> navigateTo(ScanActivity::class.java)
                R.id.nav_map -> navigateTo(MapActivity::class.java)
                R.id.nav_profile -> navigateTo(ProfileActivity::class.java)
                R.id.nav_receipt -> {

                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.history_fragment_container, ReceiptHistoryFragment())
                        .commit()
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