package com.example.reframe.data

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREFS_NAME = "reframe_prefs"
    private const val KEY_MEMBER_ID = "member_id"
    private const val KEY_AUTH_TOKEN = "auth_token"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // 로그인 성공 시 호출
    fun saveAuthInfo(context: Context, memberId: Long, token: String) {
        val editor = getPreferences(context).edit()
        editor.putLong(KEY_MEMBER_ID, memberId)
        editor.putString(KEY_AUTH_TOKEN, token)
        editor.apply()
    }

    // memberId가 필요할 때 호출
    fun getMemberId(context: Context): Long {
        return getPreferences(context).getLong(KEY_MEMBER_ID, -1L)
    }

    // 토큰이 필요할 때 호출 (예: 인증 헤더)
    fun getToken(context: Context): String? {
        return getPreferences(context).getString(KEY_AUTH_TOKEN, null)
    }
}