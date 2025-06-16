package com.example.rmai2425_projects_astromap.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.rmai2425_projects_astromap.database.Korisnik

class UserManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun saveUserSession(korisnik: Korisnik) {
        prefs.edit().apply {
            putInt(KEY_USER_ID, korisnik.id)
            putString(KEY_USER_EMAIL, korisnik.email)
            putString(KEY_USER_NAME, korisnik.ime)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    fun isUserLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getCurrentUserId(): Int = prefs.getInt(KEY_USER_ID, -1)

    fun logout() {
        prefs.edit().clear().apply()
    }
}
