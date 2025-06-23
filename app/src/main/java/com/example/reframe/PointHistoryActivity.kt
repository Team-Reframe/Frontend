package com.example.reframe

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.reframe.adapters.PointHistoryAdapter
import com.example.reframe.data.PointHistoryItem
import com.example.reframe.data.SessionManager
import com.example.reframe.databinding.ActivityPointHistoryBinding
import com.example.reframe.ui.profile.ProfileViewModel
import com.example.reframe.ui.scan.UiState
import java.text.DecimalFormat

class PointHistoryActivity : AppCompatActivity() {

    // ViewBinding과 ViewModel 초기화
    private lateinit var binding: ActivityPointHistoryBinding
    private val viewModel: ProfileViewModel by viewModels()

    // RecyclerView Adapter
    private lateinit var pointHistoryAdapter: PointHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 설정
        binding = ActivityPointHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 설정 함수들 호출
        setupToolbar()
        setupRecyclerView()
        observeViewModel()

        // 로그인된 사용자 ID로 API 데이터 요청
        val memberId = SessionManager.getMemberId(this)
        if (memberId != -1L) {
            viewModel.fetchTotalPoints(memberId)
            viewModel.fetchPointHistory(memberId)
            // 사용자 이름 표시를 위해 내 정보도 함께 요청
            viewModel.fetchMyInfo(memberId)
        } else {
            Toast.makeText(this, "로그인 정보가 없습니다.", Toast.LENGTH_SHORT).show()
            // 필요하다면 로그인 화면으로 이동시키는 로직 추가
        }
    }

    // 툴바 설정
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 타이틀 숨기기
    }

    // RecyclerView 설정
    private fun setupRecyclerView() {
        // 어댑터를 빈 리스트로 초기화
        pointHistoryAdapter = PointHistoryAdapter(emptyList())
        binding.rvPointHistory.adapter = pointHistoryAdapter
    }

    // ViewModel의 LiveData를 관찰하여 UI 업데이트
    private fun observeViewModel() {
        // 총 포인트 정보 관찰
        viewModel.totalPoints.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    val formatter = DecimalFormat("#,###")
                    binding.tvUserPointAmount.text = "${formatter.format(state.data.total)}원"
                }
                is UiState.Error -> {
                    binding.tvUserPointAmount.text = "조회 실패"
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
                is UiState.Loading -> {
                    binding.tvUserPointAmount.text = "조회중..."
                }
            }
        }

        // 포인트 내역 리스트 관찰
        viewModel.pointHistory.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                   pointHistoryAdapter.updateData(state.data)
                }
                is UiState.Error -> {
                    Toast.makeText(this, "내역 조회 실패: ${state.message}", Toast.LENGTH_SHORT).show()
                }
                is UiState.Loading -> { /* 로딩 인디케이터 표시 */ }
            }
        }

        // 사용자 정보 관찰 (이름 표시용)
        viewModel.userInfo.observe(this) { state ->
            if (state is UiState.Success) {
                setStyledTitle(state.data.name)
            }
        }
    }

    // 사용자 이름에 스타일을 적용하여 제목 설정
    private fun setStyledTitle(userName: String) {
        // strings.xml에 <string name="user_point_title"><b>%1$s</b>님의 포인트</string> 가 정의되어 있어야 함
        val htmlString = getString(R.string.user_point_title, userName)
        val spannedText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(htmlString)
        }
        binding.tvUserPointTitle.text = spannedText
    }
}