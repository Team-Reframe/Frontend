package com.example.reframe

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class MyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        val toolbar: Toolbar = findViewById(R.id.toolbar_my_info)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

          findViewById<View>(R.id.fab_edit_profile_image).setOnClickListener {
        }

        // 각 정보 항목에 데이터 설정
        setupInfoItem(findViewById(R.id.item_name), "이름", "박수연")
        setupInfoItem(findViewById(R.id.item_english_name), "영문이름", "없음")
        setupInfoItem(findViewById(R.id.item_birth_date), "생년월일", "없음")
        setupInfoItem(findViewById(R.id.item_phone_number), "휴대폰 번호", "010-1111-2222")
        setupInfoItem(findViewById(R.id.item_email), "이메일 주소", "refame@gmail.com")
        setupInfoItem(findViewById(R.id.item_password), "비밀번호", "••••••••••")

    }

    private fun setupInfoItem(itemView: View, label: String, value: String) {
        itemView.findViewById<TextView>(R.id.tv_label).text = label
        itemView.findViewById<TextView>(R.id.tv_value).text = value
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}