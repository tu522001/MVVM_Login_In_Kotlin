package com.example.mvvm_login_in_kotlin.login.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_login_in_kotlin.login.data.repository.Repository


class SignInViewModel(private val repository: Repository) : ViewModel() {

//    private val repository2 = Repository2()
    private val _signInSuccess = MutableLiveData<Boolean>()
    val signInSuccess : LiveData<Boolean> = _signInSuccess

    fun signIn(context: Context, email : String, password : String ){

        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")

        if (emailPattern.matches(email) && passwordPattern.matches(password)) {
            val result = repository.signIn(email,password)
            result.observeForever{
                _signInSuccess.value = it
            }
            Toast.makeText(context, "Hợp lệ", Toast.LENGTH_SHORT).show()
//            onCallBackSignIn.onSuccess("Hợp lệ")
        } else {
            Toast.makeText(context, "Email hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show()
//            onCallBackSignIn.onFale("Email hoặc mật khẩu không hợp lệ")
        }
    }
}
