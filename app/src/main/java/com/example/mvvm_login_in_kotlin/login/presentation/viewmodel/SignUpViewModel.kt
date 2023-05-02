package com.example.mvvm_login_in_kotlin.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_login_in_kotlin.login.data.repository.Repository

class SignUpViewModel(private val repository: Repository) : ViewModel() {
    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess : LiveData<Boolean> = _signUpSuccess

    fun signUp(firstName : String, lastName: String, email: String, phone: String, password: String, confirmPassword: String){
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")

        if (emailPattern.matches(email) && passwordPattern.matches(password)) {
            val result = repository.signUp(firstName, lastName, email, phone, password, confirmPassword)
            result.observeForever{
                _signUpSuccess.value = it
            }
        } else {
            _signUpSuccess.value = false
        }
    }
}