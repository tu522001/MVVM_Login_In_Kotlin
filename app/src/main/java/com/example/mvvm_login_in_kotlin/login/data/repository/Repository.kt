package com.example.mvvm_login_in_kotlin.login.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_login_in_kotlin.login.data.local.sharepref.PreferencesManager

class Repository(private val preferencesManager: PreferencesManager) {

    fun signIn(email: String, password: String): LiveData<Boolean> {
        val _signInSuccess = MutableLiveData<Boolean>()

        // Lấy thông tin tài khoản từ Share Preferences
        val account = preferencesManager.getInformationAccount()

        // So sánh thông tin đăng nhập với thông tin tài khoản đã lưu
        if (email == account.first && password == account.second) {
            _signInSuccess.value = true
        } else {
            _signInSuccess.value = false
        }
        return _signInSuccess
    }

    fun signUp(firstName : String, lastName: String, email: String, phone: String, password: String, confirmPassword: String): LiveData<Boolean> {
        val signUpSuccess = MutableLiveData<Boolean>()
        if (firstName != null && lastName != null && email != null && phone != null && password != null && confirmPassword != null){
            // Lưu tên email và mật khẩu vào Share Preferences
            preferencesManager.saveInformation(email, password)
            signUpSuccess.value = true
        }
        return signUpSuccess
    }

}