package com.example.chatapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.data.Resources
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.ui.MainActivity
import com.example.chatapp.ui.viewmodel.AuthViewModel
import com.example.chatapp.utils.InputTextWatcher
import com.example.chatapp.utils.TextChangeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding>(),TextChangeListener {
    override val mViewModel: AuthViewModel by viewModels()

    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
        setObserver()
    }

    private fun setObserver() {
        mViewModel.loginResponse.observe(viewLifecycleOwner){
            it?.let {
                when(it){
                    is Resources.Success->{

                    }
                    is Resources.Failure->{
                        showSnackbar("Authentication failed")
                    }
                    is Resources.Loading ->{

                    }
                }
            }

        }
    }

    private fun checkValidity(): Boolean {
        if(mViewBinding.etEmail.text?.isEmpty() == true){
            showSnackbar("Please enter your email address")
            return false
        }
        if(mViewBinding.etPassword.text?.isEmpty() == true){
            showSnackbar("Please enter your password")
            return false
        }
        return true
    }

    private fun setOnClickListener() {
        mViewBinding.btnLogin.setOnClickListener {
            if(checkValidity()){
                mViewModel.login(mViewBinding.etEmail.text.toString(),mViewBinding.etPassword.text.toString())
            }
        }
    }

    private fun initView() {
        val activity = requireActivity()
        if(activity is MainActivity){
            activity.setActionBar()
            activity.setActionBarTitle("Login")
        }
        mViewBinding.etEmail.addTextChangedListener(InputTextWatcher(this))
        mViewBinding.etPassword.addTextChangedListener(InputTextWatcher(this))
    }

    private fun checkTextField(): Boolean {
        if(mViewBinding.etEmail.text?.isNotEmpty() == true && mViewBinding.etPassword.text?.isNotEmpty()==true) {
            return true
        }
        return false
    }

    override fun onValidationPass() {
        mViewBinding.btnLogin.isEnabled = checkTextField()
    }

    override fun onValidationFailed() {
        mViewBinding.btnLogin.isEnabled = false
    }

}