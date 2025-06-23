package com.example.reframe.ui.history

import androidx.lifecycle.*
import com.example.reframe.data.dto.*
import com.example.reframe.data.network.RetrofitClient
import com.example.reframe.ui.scan.UiState
import kotlinx.coroutines.launch

class ReceiptViewModel : ViewModel() {

    // 총 포인트
    private val _totalPoints = MutableLiveData<UiState<TotalPointResponse>>()
    val totalPoints: LiveData<UiState<TotalPointResponse>> = _totalPoints

    // 영수증(포인트) 내역
    private val _receiptHistory = MutableLiveData<UiState<List<ReceiptHistoryResponse>>>()
    val receiptHistory: LiveData<UiState<List<ReceiptHistoryResponse>>> = _receiptHistory

    // 내가 쓴 리뷰 목록
    private val _myReviews = MutableLiveData<UiState<List<MyReviewResponse>>>()
    val myReviews: LiveData<UiState<List<MyReviewResponse>>> = _myReviews

    // 리뷰 등록/삭제/수정 결과
    private val _reviewPostState = MutableLiveData<UiState<Unit>>()
    val reviewPostState: LiveData<UiState<Unit>> = _reviewPostState

    private val _reviewDeleteState = MutableLiveData<UiState<Unit>>()
    val reviewDeleteState: LiveData<UiState<Unit>> = _reviewDeleteState


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

    fun fetchReceiptHistory(memberId: Long) {
        _receiptHistory.value = UiState.Loading
        viewModelScope.launch {
            try {
               val response = RetrofitClient.apiService.getReceiptHistory(memberId)
                if (response.isSuccessful) {
                    _receiptHistory.postValue(UiState.Success(response.body()!!))
                } else {
                    _receiptHistory.postValue(UiState.Error("내역 조회 실패"))
                }
            } catch (e: Exception) {
                _receiptHistory.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }

    fun fetchMyReviews(memberId: Long) {
        _myReviews.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getMyReviews(memberId)
                if (response.isSuccessful) {
                    _myReviews.postValue(UiState.Success(response.body()!!))
                } else {
                    _myReviews.postValue(UiState.Error("리뷰 조회 실패"))
                }
            } catch (e: Exception) {
                _myReviews.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }

    fun postReview(reviewRequest: ReviewRequest) {
        _reviewPostState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.postReview(reviewRequest)
                if (response.isSuccessful) {
                    _reviewPostState.postValue(UiState.Success(Unit))
                } else {
                    _reviewPostState.postValue(UiState.Error("리뷰 등록 실패"))
                }
            } catch (e: Exception) {
                _reviewPostState.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }

    fun deleteReview(reviewId: Long, memberId: Long) {
        _reviewDeleteState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.deleteReview(reviewId, memberId)
                if (response.isSuccessful) {
                    _reviewDeleteState.postValue(UiState.Success(Unit))
                } else {
                    _reviewDeleteState.postValue(UiState.Error("리뷰 삭제 실패"))
                }
            } catch (e: Exception) {
                _reviewDeleteState.postValue(UiState.Error(e.message ?: "알 수 없는 오류"))
            }
        }
    }
}