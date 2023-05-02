package com.example.mvvm_login_in_kotlin.login.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_login_in_kotlin.login.data.repository.Repository
import com.example.mvvm_login_in_kotlin.login.presentation.viewmodel.SignUpViewModel

class SignUpViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @JvmSuppressWildcards
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}