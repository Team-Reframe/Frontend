package com.example.reframe

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.databinding.ActivityWithdrawalBinding

class WithdrawalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithdrawalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 설정
        setupToolbar()

        // 탈퇴하기 버튼 클릭 리스너 설정
        binding.btnWithdraw.setOnClickListener {
            val password = binding.etPassword.text.toString()

            if (password.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 여기에 실제 비밀번호 검증 및 회원탈퇴 API 호출 로직을 구현.
                // 예를 들어, ViewModel을 통해 서버에 탈퇴 요청을 보냅니다.
                // 성공적으로 요청이 처리되면 액티비티를 종료하거나 로그인 화면으로 이동합니다.
                Toast.makeText(this, "탈퇴 처리가 요청되었습니다.", Toast.LENGTH_SHORT).show()
                // 예시로 액티비티 종료
                finish()
            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
            setDisplayShowTitleEnabled(false) // 툴바 타이틀 숨기기
        }
    }

    // 툴바의 뒤로가기 버튼 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
