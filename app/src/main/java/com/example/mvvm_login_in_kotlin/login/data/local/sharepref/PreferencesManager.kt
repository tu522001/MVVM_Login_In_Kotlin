package com.example.mvvm_login_in_kotlin.login.data.local.sharepref

import android.content.Context
import androidx.core.content.edit

class PreferencesManager(private val context: Context) {
    private val preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveInformation(email: String, password: String) {
        preferences.edit {
            putString("email", email)
            putString("password", password)
        }
    }

    fun getInformationAccount(): Pair<String, String> {
        var pair: Pair<String, String>? = null
        val email = preferences.getString("email", "") ?: ""
        val password = preferences.getString("password", "") ?: ""
        pair = Pair(email,password)
        return pair
    }

}