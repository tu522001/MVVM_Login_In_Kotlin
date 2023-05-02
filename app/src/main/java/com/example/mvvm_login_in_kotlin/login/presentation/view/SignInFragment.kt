package com.example.mvvm_login_in_kotlin.login.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvm_login_in_kotlin.R
import com.example.mvvm_login_in_kotlin.databinding.FragmentSignIn2Binding
import com.example.mvvm_login_in_kotlin.login.data.local.sharepref.PreferencesManager
import com.example.mvvm_login_in_kotlin.login.data.repository.Repository
import com.example.mvvm_login_in_kotlin.login.presentation.viewmodel.SignInViewModel
import com.example.mvvm_login_in_kotlin.login.onCallBackSignIn
import com.example.mvvm_login_in_kotlin.login.presentation.viewmodelfactory.SignInViewModelFactory


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignIn2Binding
    private lateinit var viewModel: SignInViewModel
    private lateinit var onCallBackSignIn: onCallBackSignIn
    private lateinit var preferencesManager: PreferencesManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in2, container, false)

        // Tạo một instance của PreferencesManager và Repository2
        val preferencesManager = PreferencesManager(requireContext())
        val repository = Repository(preferencesManager)

        // Tạo một instance của SignInViewModelFactory với Repository2 được truyền vào
        val viewModelFactory = SignInViewModelFactory(repository)

        // Tạo một instance của SignInViewModel từ SignInViewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)


        onCallBackSignIn = object : onCallBackSignIn {
            override fun onSuccess(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }

            override fun onFale(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        binding.txtRegister.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment2_to_signUpFragment2)
        }

        binding.button.setOnClickListener {
            var email = binding.editTextEmail.text.toString()
            var password = binding.editTextPassword.text.toString()
            viewModel.signIn(this.requireContext(),email, password)
        }

        viewModel.signInSuccess.observe(requireActivity(), Observer { signInSuccess ->
            if (signInSuccess){
                Toast.makeText(this.requireContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.requireContext(), "Đăng nhập không thành công, vui lòng kiểm tra email và mật khẩu của bạn", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

}