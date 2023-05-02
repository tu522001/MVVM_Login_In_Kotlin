package com.example.mvvm_login_in_kotlin.login.data.local.model

data class SignUp_Model(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirmPassword: String
)