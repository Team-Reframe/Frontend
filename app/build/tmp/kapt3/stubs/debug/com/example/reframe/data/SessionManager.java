package com.example.reframe.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/reframe/data/SessionManager;", "", "()V", "KEY_AUTH_TOKEN", "", "KEY_MEMBER_ID", "PREFS_NAME", "clearData", "", "context", "Landroid/content/Context;", "getMemberId", "", "getPreferences", "Landroid/content/SharedPreferences;", "getToken", "saveAuthInfo", "memberId", "token", "app_debug"})
public final class SessionManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "reframe_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_MEMBER_ID = "member_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AUTH_TOKEN = "auth_token";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.reframe.data.SessionManager INSTANCE = null;
    
    private SessionManager() {
        super();
    }
    
    private final android.content.SharedPreferences getPreferences(android.content.Context context) {
        return null;
    }
    
    public final void saveAuthInfo(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long memberId, @org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final long getMemberId(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getToken(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void clearData(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}