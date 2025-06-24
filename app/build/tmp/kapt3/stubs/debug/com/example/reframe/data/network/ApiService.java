package com.example.reframe.data.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001d\u001a\u00020\u001eH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010!\u001a\u00020\"H\u00a7@\u00a2\u0006\u0002\u0010#J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010&\u001a\u00020\'H\u00a7@\u00a2\u0006\u0002\u0010(J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00032\b\b\u0001\u0010\u0019\u001a\u00020+H\u00a7@\u00a2\u0006\u0002\u0010,J(\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010/\u001a\u000200H\u00a7@\u00a2\u0006\u0002\u00101\u00a8\u00062"}, d2 = {"Lcom/example/reframe/data/network/ApiService;", "", "deleteReview", "Lretrofit2/Response;", "", "reviewId", "", "memberId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyInfo", "Lcom/example/reframe/data/dto/UserInfoResponse;", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyReviews", "", "Lcom/example/reframe/data/dto/MyReviewResponse;", "getPointHistory", "Lcom/example/reframe/data/PointHistoryItem;", "getReceiptHistory", "Lcom/example/reframe/data/dto/ReceiptHistoryResponse;", "getReviewDetail", "Lcom/example/reframe/data/dto/ReviewDetailResponse;", "getTotalPoints", "Lcom/example/reframe/data/dto/TotalPointResponse;", "login", "Lcom/example/reframe/data/dto/LoginResponse;", "request", "Lcom/example/reframe/data/dto/LoginRequest;", "(Lcom/example/reframe/data/dto/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postReview", "reviewRequest", "Lcom/example/reframe/data/dto/ReviewRequest;", "(Lcom/example/reframe/data/dto/ReviewRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestReward", "Lcom/example/reframe/data/dto/PointResponse;", "rewardRequest", "Lcom/example/reframe/data/dto/RewardRequest;", "(Lcom/example/reframe/data/dto/RewardRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUp", "Lcom/example/reframe/data/dto/SignUpResponse;", "Lcom/example/reframe/data/dto/SignUpRequest;", "(Lcom/example/reframe/data/dto/SignUpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadReceipt", "Lcom/example/reframe/data/dto/ReceiptUploadResponse;", "file", "Lokhttp3/MultipartBody$Part;", "(JLokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "/receipts/upload")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadReceipt(@retrofit2.http.Query(value = "memberId")
    long memberId, @retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.ReceiptUploadResponse>> $completion);
    
    @retrofit2.http.POST(value = "/reward")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object requestReward(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.data.dto.RewardRequest rewardRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.PointResponse>> $completion);
    
    @retrofit2.http.POST(value = "/member/sign-up")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signUp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.data.dto.SignUpRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.SignUpResponse>> $completion);
    
    @retrofit2.http.POST(value = "/auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.data.dto.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.LoginResponse>> $completion);
    
    @retrofit2.http.GET(value = "/point/total")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalPoints(@retrofit2.http.Query(value = "memberId")
    long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.TotalPointResponse>> $completion);
    
    @retrofit2.http.GET(value = "/point/history")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReceiptHistory(@retrofit2.http.Query(value = "memberId")
    long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.reframe.data.dto.ReceiptHistoryResponse>>> $completion);
    
    @retrofit2.http.POST(value = "/reviews")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object postReview(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.reframe.data.dto.ReviewRequest reviewRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "/reviews/my")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMyReviews(@retrofit2.http.Query(value = "memberId")
    long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.reframe.data.dto.MyReviewResponse>>> $completion);
    
    @retrofit2.http.DELETE(value = "/reviews/{reviewId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReview(@retrofit2.http.Path(value = "reviewId")
    long reviewId, @retrofit2.http.Query(value = "memberId")
    long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "/reviews/{reviewId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReviewDetail(@retrofit2.http.Path(value = "reviewId")
    long reviewId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.ReviewDetailResponse>> $completion);
    
    @retrofit2.http.GET(value = "/member/my-info")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMyInfo(@retrofit2.http.Query(value = "memberId")
    long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.reframe.data.dto.UserInfoResponse>> $completion);
    
    @retrofit2.http.GET(value = "/point/history")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPointHistory(@retrofit2.http.Query(value = "memberId")
    long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.reframe.data.PointHistoryItem>>> $completion);
    
    @retrofit2.http.POST(value = "/auth/logout")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object logout(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
}