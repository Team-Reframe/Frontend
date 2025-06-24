package com.example.reframe.ui.history;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010 \u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010!\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R#\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012\u00a8\u0006%"}, d2 = {"Lcom/example/reframe/ui/history/ReceiptViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_myReviews", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/reframe/ui/scan/UiState;", "", "Lcom/example/reframe/data/dto/MyReviewResponse;", "_receiptHistory", "Lcom/example/reframe/data/dto/ReceiptHistoryResponse;", "_reviewDeleteState", "", "_reviewPostState", "_totalPoints", "Lcom/example/reframe/data/dto/TotalPointResponse;", "myReviews", "Landroidx/lifecycle/LiveData;", "getMyReviews", "()Landroidx/lifecycle/LiveData;", "receiptHistory", "getReceiptHistory", "reviewDeleteState", "getReviewDeleteState", "reviewPostState", "getReviewPostState", "totalPoints", "getTotalPoints", "deleteReview", "reviewId", "", "memberId", "fetchMyReviews", "fetchReceiptHistory", "fetchTotalPoints", "postReview", "reviewRequest", "Lcom/example/reframe/data/dto/ReviewRequest;", "app_debug"})
public final class ReceiptViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.TotalPointResponse>> _totalPoints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.TotalPointResponse>> totalPoints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.dto.ReceiptHistoryResponse>>> _receiptHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.dto.ReceiptHistoryResponse>>> receiptHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.dto.MyReviewResponse>>> _myReviews = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.dto.MyReviewResponse>>> myReviews = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> _reviewPostState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> reviewPostState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> _reviewDeleteState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> reviewDeleteState = null;
    
    public ReceiptViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.TotalPointResponse>> getTotalPoints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.dto.ReceiptHistoryResponse>>> getReceiptHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.dto.MyReviewResponse>>> getMyReviews() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> getReviewPostState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> getReviewDeleteState() {
        return null;
    }
    
    public final void fetchTotalPoints(long memberId) {
    }
    
    public final void fetchReceiptHistory(long memberId) {
    }
    
    public final void fetchMyReviews(long memberId) {
    }
    
    public final void postReview(@org.jetbrains.annotations.NotNull()
    com.example.reframe.data.dto.ReviewRequest reviewRequest) {
    }
    
    public final void deleteReview(long reviewId, long memberId) {
    }
}