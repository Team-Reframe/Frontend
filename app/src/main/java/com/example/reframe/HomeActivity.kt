package com.example.reframe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.reframe.api.TotalResponse
import com.example.reframe.api.RetrofitClient
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.example.reframe.api.StoreResponse
import com.example.reframe.ui.profile.ProfileActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // ✅ 현재 날짜 구하기
        val today = Date()
        val sdf = SimpleDateFormat("M월 d일 E요일", Locale.KOREAN)
        val dateStr = sdf.format(today)

        // ✅ TextView 찾아서 현재 날짜로 바꾸기
        val tvPointDate = findViewById<TextView>(R.id.tvPointDate)
        tvPointDate.text = dateStr

        // ✅ LoginActivity에서 넘어온 이름 받기
        val userName = intent.getStringExtra("USER_NAME") ?: "사용자"

        // ✅ 이름 TextView 설정
        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        tvUserName.text = userName

        // ✅ 인사말 TextView 설정
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        tvGreeting.text = "님, 반갑습니다."

        // ✅ 이미 ApiService 타입이므로 바로 사용!
        val apiService = RetrofitClient.instance
        val tvPointAmount = findViewById<TextView>(R.id.tvPointAmount)

// 예: Login 후 저장한 memberId 사용
        val memberId = 7L // 또는 SharedPreferences 등에서 받아오기

        apiService.getTotalPoints(memberId).enqueue(object : retrofit2.Callback<TotalResponse> {
            override fun onResponse(call: Call<TotalResponse>, response: Response<TotalResponse>) {
                if (response.isSuccessful) {
                    val total = response.body()?.total ?: 0
                    tvPointAmount.text = String.format("%,d원", total)
                } else {
                    tvPointAmount.text = "0원"
                }
            }

            override fun onFailure(call: Call<TotalResponse>, t: Throwable) {
                t.printStackTrace()
                tvPointAmount.text = "0원"
            }
        })
        val storeRecyclerView = findViewById<RecyclerView>(R.id.home_store_list)

// ✅ Home 화면은 HomeStoreAdapter 사용!
        val storeAdapter = HomeStoreAdapter { store ->
            val intent = Intent(this, StoreDetailActivity::class.java).apply {
                putExtra("name", store.name)
                putExtra("address", store.address)
                putExtra("phone", store.phone ?: "전화번호 없음")
            }
            startActivity(intent)
        }

        storeRecyclerView.adapter = storeAdapter
        storeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

// ✅ [MapActivity 참고] 가맹점 이미지 더미 지정
        RetrofitClient.instance.getAllStores().enqueue(object : Callback<List<StoreResponse>> {
            override fun onResponse(call: Call<List<StoreResponse>>, response: Response<List<StoreResponse>>) {
                if (response.isSuccessful) {
                    val stores = response.body() ?: emptyList()

                    val imageResIds = listOf(
                        R.drawable.siheung,
                        R.drawable.bread,
                        R.drawable.cafe,
                        R.drawable.cu
                    )

                    stores.forEachIndexed { index, store ->
                        store.imageResId = if (index < imageResIds.size) {
                            imageResIds[index]
                        } else {
                            R.drawable.receipt_image // 기본 이미지
                        }
                    }

                    storeAdapter.submitList(stores)

                } else {
                    Toast.makeText(this@HomeActivity, "가맹점 불러오기 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<StoreResponse>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@HomeActivity, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


        // ✅ BottomNavigationView 코드 수정했습니다!
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_home // 홈 아이템을 선택된 상태로 표시

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_receipt -> navigateTo(com.example.reframe.ui.history.ReceiptHistoryActivity::class.java)
                R.id.nav_scan -> navigateTo(com.example.reframe.ui.scan.ScanActivity::class.java)
                R.id.nav_map -> navigateTo(MapActivity::class.java)
                R.id.nav_profile -> navigateTo(com.example.reframe.ui.profile.ProfileActivity::class.java)
                else -> false
            }
        }
    }
    private fun navigateTo(activityClass: Class<*>): Boolean {
        if (this.javaClass == activityClass) {
            return true
        }
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        overridePendingTransition(0, 0)
        return true
    }
}
