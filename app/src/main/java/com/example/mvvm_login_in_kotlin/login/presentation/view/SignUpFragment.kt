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
import com.example.mvvm_login_in_kotlin.databinding.FragmentSignUp2Binding
import com.example.mvvm_login_in_kotlin.login.data.local.sharepref.PreferencesManager
import com.example.mvvm_login_in_kotlin.login.data.repository.Repository
import com.example.mvvm_login_in_kotlin.login.presentation.viewmodel.SignUpViewModel
import com.example.mvvm_login_in_kotlin.login.presentation.viewmodelfactory.SignUpViewModelFactory

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUp2Binding
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up2, container, false)

        preferencesManager = PreferencesManager(requireContext())
        val repository = Repository(preferencesManager)

        // Tạo một instance của SignUpViewModelFactory với Repository2 được truyền vào
        val viewModelFactory = SignUpViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment2_to_signInFragment2)
        }
        binding.btnXacNhan.setOnClickListener {
            var firstName = binding.edtFirstName.text.toString()
            var lastName = binding.editLastName.text.toString()
            var email = binding.edtEmail.text.toString()
            var phone = binding.edtPhone.text.toString()
            var password = binding.edtPassword.text.toString()
            var confirmPassword = binding.edtConfirmPassword.text.toString()
            viewModel.signUp(firstName, lastName, email, phone, password, confirmPassword)
        }

        viewModel.signUpSuccess.observe(viewLifecycleOwner, Observer { signUpSuccess->

            if (signUpSuccess){
                Toast.makeText(requireContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Đăng ký chưa thành công", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }


}