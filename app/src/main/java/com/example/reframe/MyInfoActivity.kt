package com.example.reframe

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.reframe.data.SessionManager // SessionManager 임포트
import com.example.reframe.data.dto.UserInfoResponse
import com.example.reframe.ui.profile.ProfileViewModel
import com.example.reframe.ui.scan.UiState

class MyInfoActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        val toolbar: Toolbar = findViewById(R.id.toolbar_my_info)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<View>(R.id.fab_edit_profile_image).setOnClickListener {
            // 프로필 이미지 편집 로직
        }

        observeViewModel()

        // 로그인된 사용자 ID로 내 정보 요청
        val memberId = SessionManager.getMemberId(this)
        if (memberId != -1L) {
            viewModel.fetchMyInfo(memberId)
        } else {
            Toast.makeText(this, "사용자 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.userInfo.observe(this) { state ->
            when (state) {
                is UiState.Loading -> { /* 로딩 인디케이터 표시 */ }
                is UiState.Success -> {
                    updateUi(state.data)
                }
                is UiState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUi(userInfo: UserInfoResponse) {
        (findViewById<View>(R.id.toolbar_title_container).findViewById<TextView>(R.id.tv_toolbar_user_name)).text = "${userInfo.name}님의 정보"
        setupInfoItem(findViewById(R.id.item_name), "이름", userInfo.name)
        setupInfoItem(findViewById(R.id.item_english_name), "영문이름", userInfo.englishName ?: "없음")
        setupInfoItem(findViewById(R.id.item_birth_date), "생년월일", userInfo.birthDate ?: "없음")
        setupInfoItem(findViewById(R.id.item_phone_number), "휴대폰 번호", userInfo.phoneNumber ?: "없음")
        setupInfoItem(findViewById(R.id.item_email), "이메일 주소", userInfo.email)
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