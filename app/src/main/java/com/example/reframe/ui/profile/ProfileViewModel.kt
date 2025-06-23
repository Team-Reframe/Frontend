package com.example.reframe.ui.profile

import androidx.lifecycle.*
import com.example.reframe.data.PointHistoryItem
import com.example.reframe.data.dto.TotalPointResponse
import com.example.reframe.data.dto.UserInfoResponse
import com.example.reframe.data.network.RetrofitClient
import com.example.reframe.ui.scan.UiState
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    // 내 정보
    private val _userInfo = MutableLiveData<UiState<UserInfoResponse>>()
    val userInfo: LiveData<UiState<UserInfoResponse>> = _userInfo

    // 총 포인트
    private val _totalPoints = MutableLiveData<UiState<TotalPointResponse>>()
    val totalPoints: LiveData<UiState<TotalPointResponse>> = _totalPoints

    // 포인트 내역
    private val _pointHistory = MutableLiveData<UiState<List<PointHistoryItem>>>()
    val pointHistory: LiveData<UiState<List<PointHistoryItem>>> = _pointHistory

    // 로그아웃
    private val _logoutState = MutableLiveData<UiState<Unit>>()
    val logoutState: LiveData<UiState<Unit>> = _logoutState

    fun fetchMyInfo(memberId: Long) {
        _userInfo.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getMyInfo(memberId)
                if (response.isSuccessful) {
                    _userInfo.postValue(UiState.Success(response.body()!!))
                } else {
                    _userInfo.postValue(UiState.Error("정보 조회 실패"))
                }
            } catch (e: Exception) {
                _userInfo.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }

    fun fetchTotalPoints(memberId: Long) {
        _totalPoints.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getTotalPoints(memberId)
                if (response.isSuccessful) {
                    _totalPoints.postValue(UiState.Success(response.body()!!))
                } else {
                    _totalPoints.postValue(UiState.Error("포인트 조회 실패"))
                }
            } catch (e: Exception) {
                _totalPoints.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }

    fun fetchPointHistory(memberId: Long) {
        _pointHistory.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPointHistory(memberId)
                if (response.isSuccessful) {
                    _pointHistory.postValue(UiState.Success(response.body()!!))
                } else {
                    _pointHistory.postValue(UiState.Error("내역 조회 실패"))
                }
            } catch (e: Exception) {
                _pointHistory.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }

    fun logout(token: String) {
        _logoutState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.logout("Bearer $token")
                if (response.isSuccessful) {
                    _logoutState.postValue(UiState.Success(Unit))
                } else {
                    _logoutState.postValue(UiState.Error("로그아웃 실패"))
                }
            } catch (e: Exception) {
                _logoutState.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }
}