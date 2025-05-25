package com.example.reframe

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kakao.vectormap.*
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle

class MapActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapView: MapView
    private lateinit var progressBar: ProgressBar
    private var startPosition: LatLng = LatLng.from(37.5665, 126.9780) // fallback: 서울
    private var kakaoMap: KakaoMap? = null

    private var requestingLocationUpdates = false
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var centerLabel: Label

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var recyclerView: RecyclerView
    private lateinit var sheetTitle: TextView

    private val readyCallback = object : KakaoMapReadyCallback() {
        override fun onMapReady(map: KakaoMap) {
            Log.d("KakaoMap", "✅ onMapReady 호출됨")
            progressBar.visibility = View.GONE
            kakaoMap = map

            val layer = kakaoMap?.labelManager?.getLayer()
            layer?.let {
                centerLabel = it.addLabel(
                    LabelOptions.from("center", startPosition)
                        .setStyles(
                            LabelStyle.from(R.drawable.red_dot_marker)
                                .setAnchorPoint(0.5f, 0.5f)
                        )
                )
                kakaoMap?.trackingManager?.startTracking(centerLabel)
            }

            startLocationUpdates()
        }

        override fun getPosition(): LatLng = startPosition
        override fun getZoomLevel(): Int = 17
    }

    inner class MyMapLifeCycleCallback : MapLifeCycleCallback() {
        override fun onMapDestroy() {
            Log.d("KakaoMap", "지도 종료됨")
        }

        override fun onMapError(error: Exception) {
            Log.e("KakaoMap", "지도 에러: ${error.message}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KakaoMapSdk.init(this, "ee292f2b71c5823748b09bb6d9c5ba4c")
        setContentView(R.layout.activity_map)



        // ✅ 네비게이션 바 세팅
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_map
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_receipt -> {
                    startActivity(Intent(this, ReceiptActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_scanner -> {
                    startActivity(Intent(this, ScannerActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_map -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }

        // ✅ 지도 & 위치 초기화
        mapView = findViewById(R.id.map_view)
        progressBar = findViewById(R.id.progressBar)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000L).build()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.locations.forEach {
                    centerLabel.moveTo(LatLng.from(it.latitude, it.longitude))
                }
            }
        }

        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val screenHeight = resources.displayMetrics.heightPixels  // 전체 화면 높이(px)
        bottomSheetBehavior.peekHeight = (screenHeight * 0.20).toInt()  // 화면의 12%만큼 peekHeight 설정

        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED


        sheetTitle = findViewById(R.id.bottom_sheet_title)
        recyclerView = findViewById(R.id.recommend_list)

        if (hasLocationPermission()) {
            getStartLocation()
        } else {
            ActivityCompat.requestPermissions(this, locationPermissions, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        if (requestingLocationUpdates) startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun hasLocationPermission(): Boolean {
        return locationPermissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    @SuppressLint("MissingPermission")
    private fun getStartLocation() {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    startPosition = LatLng.from(location.latitude, location.longitude)
                } else {
                    Toast.makeText(this, "⚠️ 위치 못 가져와 기본 위치로 시작", Toast.LENGTH_SHORT).show()
                }
                mapView.start(MyMapLifeCycleCallback(), readyCallback)
            }
            .addOnFailureListener {
                Toast.makeText(this, "⚠️ 위치 요청 실패", Toast.LENGTH_SHORT).show()
                mapView.start(MyMapLifeCycleCallback(), readyCallback)
            }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        requestingLocationUpdates = true
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                getStartLocation()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setMessage("위치 권한이 없으면 지도를 사용할 수 없습니다.")
            .setPositiveButton("앱 설정") { _, _ ->
                startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.parse("package:$packageName")
                })
                finish()
            }
            .setNegativeButton("앱 종료") { _, _ -> finish() }
            .setCancelable(false)
            .show()
    }
}
