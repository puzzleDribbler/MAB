package com.example.mehfu.mab.activities

import android.content.Context

class SessionManager(val con: Context){

    companion object {
        val PREF_NAME: String = "Admin"
        val IS_LOGGED_IN : String = "isLoggedIn"
        val KEY_USERNAME : String = "username"
    }

    fun createLoginSession(email: String) {
        val pref = con.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(KEY_USERNAME, email)
        editor.apply()
    }

    fun logout() {
        val pref = con.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val pref = con.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return pref.getBoolean(IS_LOGGED_IN, false)
    }

}