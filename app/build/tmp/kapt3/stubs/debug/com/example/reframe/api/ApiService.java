package com.example.reframe.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J<\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0010\u001a\u00020\u000f2\b\b\u0001\u0010\u0011\u001a\u00020\u000f2\b\b\u0001\u0010\u0012\u001a\u00020\u000fH\'J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\'J\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0018H\'J\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u001bH\'\u00a8\u0006\u001c"}, d2 = {"Lcom/example/reframe/api/ApiService;", "", "getAllStores", "Lretrofit2/Call;", "", "Lcom/example/reframe/api/StoreResponse;", "getMyInfo", "Lcom/example/reframe/api/MyInfoResponse;", "token", "", "getStoreDetail", "id", "", "getStoresInMap", "swLat", "", "swLng", "neLat", "neLng", "importStore", "request", "Lcom/example/reframe/api/StoreImportRequest;", "login", "Lcom/example/reframe/api/LoginResponse;", "Lcom/example/reframe/api/LoginRequest;", "signUp", "Lcom/example/reframe/api/SignupResponse;", "Lcom/example/reframe/api/SignupRequest;", "app_debug"})
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
}