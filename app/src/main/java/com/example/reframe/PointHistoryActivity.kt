package com.example.reframe

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.data.PointHistoryItem
import com.example.reframe.adapters.PointHistoryAdapter
import com.example.reframe.databinding.ActivityPointHistoryBinding

class PointHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPointHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setStyledTitle()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setStyledTitle() {
        val htmlString = getString(R.string.user_point_title)

        val spannedText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(htmlString)
        }
        binding.tvUserPointTitle.text = spannedText
    }

    private fun setupRecyclerView() {
        // 임시 데이터 생성
        val historyList = listOf(
            PointHistoryItem("**가맹점", "2025.xx.xx", "+xx p"),
            PointHistoryItem("**가맹점", "2025.xx.xx", "+xx p"),
            PointHistoryItem("**가맹점", "2025.xx.xx", "+xx p"),
            PointHistoryItem("**가맹점", "2025.xx.xx", "+xx p"),
            PointHistoryItem("**가맹점", "2025.xx.xx", "+xx p")
        )
        val adapter = PointHistoryAdapter(historyList)
        binding.rvPointHistory.adapter = adapter
    }
}
