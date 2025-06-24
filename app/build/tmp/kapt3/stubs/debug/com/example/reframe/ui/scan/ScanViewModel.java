package com.example.reframe.ui.scan;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0019R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u001c"}, d2 = {"Lcom/example/reframe/ui/scan/ScanViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_rewardState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/reframe/ui/scan/UiState;", "Lcom/example/reframe/data/dto/PointResponse;", "_uploadState", "Lcom/example/reframe/data/dto/ReceiptUploadResponse;", "rewardState", "Landroidx/lifecycle/LiveData;", "getRewardState", "()Landroidx/lifecycle/LiveData;", "uploadState", "getUploadState", "claimReward", "", "purchaseId", "", "memberId", "createImageMultipart", "Lokhttp3/MultipartBody$Part;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "uploadReceipt", "imageUri", "app_debug"})
public final class ScanViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.ReceiptUploadResponse>> _uploadState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.ReceiptUploadResponse>> uploadState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.PointResponse>> _rewardState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.PointResponse>> rewardState = null;
    
    public ScanViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.ReceiptUploadResponse>> getUploadState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.PointResponse>> getRewardState() {
        return null;
    }
    
    public final void uploadReceipt(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long memberId, @org.jetbrains.annotations.NotNull()
    android.net.Uri imageUri) {
    }
    
    public final void claimReward(long purchaseId, long memberId) {
    }
    
    private final okhttp3.MultipartBody.Part createImageMultipart(android.content.Context context, android.net.Uri uri) {
        return null;
    }
}