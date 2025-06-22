package com.example.reframe.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0003H\'J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\'J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\'J\u001e\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\t0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\'J<\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0016\u001a\u00020\u00152\b\b\u0001\u0010\u0017\u001a\u00020\u00152\b\b\u0001\u0010\u0018\u001a\u00020\u0015H\'J\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u001dH\'J\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\b\b\u0001\u0010\u001c\u001a\u00020 H\'J\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\b\b\u0001\u0010\u001c\u001a\u00020#H\'\u00a8\u0006$"}, d2 = {"Lcom/example/reframe/api/ApiService;", "", "addLove", "Lretrofit2/Call;", "Ljava/lang/Void;", "memberId", "", "storeId", "getAllStores", "", "Lcom/example/reframe/api/StoreResponse;", "getMyInfo", "Lcom/example/reframe/api/MyInfoResponse;", "token", "", "getStoreDetail", "id", "getStoreReviews", "Lcom/example/reframe/api/ReviewResponse;", "getStoresInMap", "swLat", "", "swLng", "neLat", "neLng", "getTotalPoints", "Lcom/example/reframe/api/TotalResponse;", "importStore", "request", "Lcom/example/reframe/api/StoreImportRequest;", "login", "Lcom/example/reframe/api/LoginResponse;", "Lcom/example/reframe/api/LoginRequest;", "signUp", "Lcom/example/reframe/api/SignupResponse;", "Lcom/example/reframe/api/SignupRequest;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.reframe.api.LoginResponse> login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.api.LoginRequest request);
    
    @retrofit2.http.POST(value = "member/sign-up")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.reframe.api.SignupResponse> signUp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.api.SignupRequest request);
    
    @retrofit2.http.GET(value = "members/me")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.reframe.api.MyInfoResponse> getMyInfo(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token);
    
    @retrofit2.http.GET(value = "total")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.reframe.api.TotalResponse> getTotalPoints(@retrofit2.http.Query(value = "memberId")
    long memberId);
    
    @retrofit2.http.GET(value = "reviews/store/{storeId}")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<java.util.List<com.example.reframe.api.ReviewResponse>> getStoreReviews(@retrofit2.http.Path(value = "storeId")
    long storeId);
    
    @retrofit2.http.POST(value = "stores/import")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.reframe.api.StoreResponse> importStore(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.api.StoreImportRequest request);
    
    @retrofit2.http.GET(value = "stores")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<java.util.List<com.example.reframe.api.StoreResponse>> getAllStores();
    
    @retrofit2.http.GET(value = "stores/{storeId}")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.reframe.api.StoreResponse> getStoreDetail(@retrofit2.http.Path(value = "storeId")
    long id);
    
    @retrofit2.http.GET(value = "stores/map")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<java.util.List<com.example.reframe.api.StoreResponse>> getStoresInMap(@retrofit2.http.Query(value = "swLat")
    double swLat, @retrofit2.http.Query(value = "swLng")
    double swLng, @retrofit2.http.Query(value = "neLat")
    double neLat, @retrofit2.http.Query(value = "neLng")
    double neLng);
    
    @retrofit2.http.POST(value = "love/add")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<java.lang.Void> addLove(@retrofit2.http.Query(value = "memberId")
    long memberId, @retrofit2.http.Query(value = "storeId")
    long storeId);
}