package com.example.reframe.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001fR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R#\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u00a8\u0006 "}, d2 = {"Lcom/example/reframe/ui/profile/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_logoutState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/reframe/ui/scan/UiState;", "", "_pointHistory", "", "Lcom/example/reframe/data/PointHistoryItem;", "_totalPoints", "Lcom/example/reframe/data/dto/TotalPointResponse;", "_userInfo", "Lcom/example/reframe/data/dto/UserInfoResponse;", "logoutState", "Landroidx/lifecycle/LiveData;", "getLogoutState", "()Landroidx/lifecycle/LiveData;", "pointHistory", "getPointHistory", "totalPoints", "getTotalPoints", "userInfo", "getUserInfo", "fetchMyInfo", "memberId", "", "fetchPointHistory", "fetchTotalPoints", "logout", "token", "", "app_debug"})
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.UserInfoResponse>> _userInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.UserInfoResponse>> userInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.TotalPointResponse>> _totalPoints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.TotalPointResponse>> totalPoints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.PointHistoryItem>>> _pointHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.PointHistoryItem>>> pointHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> _logoutState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> logoutState = null;
    
    public ProfileViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.UserInfoResponse>> getUserInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<com.example.reframe.data.dto.TotalPointResponse>> getTotalPoints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<java.util.List<com.example.reframe.data.PointHistoryItem>>> getPointHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.reframe.ui.scan.UiState<kotlin.Unit>> getLogoutState() {
        return null;
    }
    
    public final void fetchMyInfo(long memberId) {
    }
    
    public final void fetchTotalPoints(long memberId) {
    }
    
    public final void fetchPointHistory(long memberId) {
    }
    
    public final void logout(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
}