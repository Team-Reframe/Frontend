package com.example.reframe.ui.scan

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reframe.data.dto.PointResponse
import com.example.reframe.data.dto.ReceiptUploadResponse
import com.example.reframe.data.dto.RewardRequest
import com.example.reframe.data.network.RetrofitClient
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class ScanViewModel : ViewModel() {

    private val _uploadState = MutableLiveData<UiState<ReceiptUploadResponse>>()
    val uploadState: LiveData<UiState<ReceiptUploadResponse>> = _uploadState

    private val _rewardState = MutableLiveData<UiState<PointResponse>>()
    val rewardState: LiveData<UiState<PointResponse>> = _rewardState

    fun uploadReceipt(context: Context, memberId: Long, imageUri: Uri) {
        _uploadState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val imagePart = createImageMultipart(context, imageUri)
                val response = RetrofitClient.apiService.uploadReceipt(memberId, imagePart)

                if (response.isSuccessful && response.body() != null) {
                    _uploadState.postValue(UiState.Success(response.body()!!))
                } else {
                    _uploadState.postValue(UiState.Error("업로드 실패: ${response.code()}"))
                }
            } catch (e: Exception) {
                _uploadState.postValue(UiState.Error("오류 발생: ${e.message}"))
            }
        }
    }

    fun claimReward(purchaseId: Long, memberId: Long) {
        _rewardState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val request = RewardRequest(purchaseId = purchaseId, memberId = memberId, amount = 0)
                val response = RetrofitClient.apiService.requestReward(request)

                if (response.isSuccessful && response.body() != null) {
                    _rewardState.postValue(UiState.Success(response.body()!!))
                } else {
                    _rewardState.postValue(UiState.Error("리워드 요청 실패: ${response.code()}"))
                }
            } catch (e: Exception) {
                _rewardState.postValue(UiState.Error("오류 발생: ${e.message}"))
            }
        }
    }

    private fun createImageMultipart(context: Context, uri: Uri): MultipartBody.Part {
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileBytes = inputStream!!.readBytes()
        inputStream.close()

        val requestBody = fileBytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", "receipt.jpg", requestBody)
    }
}