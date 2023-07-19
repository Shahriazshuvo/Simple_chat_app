package com.example.chatapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.data.Resources
import com.example.chatapp.databinding.FragmentSignUpBinding
import com.example.chatapp.ui.MainActivity
import com.example.chatapp.ui.viewmodel.AuthViewModel
import com.example.chatapp.utils.InputTextWatcher
import com.example.chatapp.utils.TextChangeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<AuthViewModel, FragmentSignUpBinding>(), TextChangeListener {
    override val mViewModel: AuthViewModel by viewModels()

    override fun getViewBinding(): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObserver()
        setOnClickListener()
    }

    private fun initView() {
        val activity = requireActivity()
        if (activity is MainActivity) {
            activity.setActionBar()
            activity.setActionBarTitle("Sign Up")
        }
        mViewBinding.etEmail.addTextChangedListener(InputTextWatcher(this))
        mViewBinding.etPassword.addTextChangedListener(
            InputTextWatcher(
                this
            )
        )
        mViewBinding.etUserName.addTextChangedListener(
            InputTextWatcher(
                this,
            )
        )
    }

    private fun setOnClickListener() {
        mViewBinding.btnRegister.setOnClickListener {
            if (checkValidity()) {
                mViewModel.signup(
                    mViewBinding.etEmail.text.toString(),
                    mViewBinding.etPassword.text.toString(),
                    mViewBinding.etUserName.text.toString()
                )
            }
        }
    }

    private fun checkValidity(): Boolean {
        if (mViewBinding.etEmail.text.toString().isEmpty()) {
            return false
        }
        if (mViewBinding.etPassword.text.toString().isEmpty()) {
            return false
        }
        if (mViewBinding.etPassword.text.toString().length < 4) {
            showSnackbar("Password length is not less than 4")
        }
        return true
    }

    private fun setUpObserver() {
        mViewModel.signUpResponse.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    is Resources.Success -> {
                        val result = it.result
                        val userId = result.uid
                        mViewModel.setUserInfoDatabase(
                            userId,
                            mViewBinding.etUserName.text.toString()
                        )
                    }
                    is Resources.Failure -> {
                        val exception = it.exception
                        showSnackbar(exception.message.toString())
                    }
                    is Resources.Loading -> {

                    }
                }
            }
        }

        mViewModel.isUserInfoSave.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    is Resources.Success -> {
                        findNavController().navigate(com.example.chatapp.R.id.action_signUpFragment_to_loginFragment)
                    }
                    is Resources.Failure -> {
                        val exception = it.exception
                    }
                    is Resources.Loading -> {

                    }
                }
            }
        }
    }

    private fun checkTextField(): Boolean {
        if (mViewBinding.etEmail.text?.isNotEmpty() == true && mViewBinding.etPassword.text?.isNotEmpty() == true && mViewBinding.etUserName.text?.isNotEmpty() == true) {
            return true
        }
        return false
    }

    override fun onValidationPass() {
        mViewBinding.btnRegister.isEnabled = checkTextField()
    }

    override fun onValidationFailed() {
        mViewBinding.btnRegister.isEnabled = false
    }

}