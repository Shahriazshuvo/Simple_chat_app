package com.example.chatapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.data.Resources
import com.example.chatapp.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthenticationRepository):BaseViewModel() {
    private val _loginResponse = MutableLiveData<Resources<FirebaseUser>?>(null)
    val loginResponse: LiveData<Resources<FirebaseUser>?> = _loginResponse

    private val _signUpResponse = MutableLiveData<Resources<FirebaseUser>?>(null)
    val signUpResponse: LiveData<Resources<FirebaseUser>?> = _signUpResponse

    private val _isUserInfoSave = MutableLiveData<Resources<Void>>(null)
    val isUserInfoSave : LiveData<Resources<Void>?> = _isUserInfoSave

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if(repository.currentUser != null){
            _loginResponse.value = Resources.Success(repository.currentUser!!)
        }
    }

    fun signup(email: String, pass: String, userName: String) {
        viewModelScope.launch {
            _signUpResponse.value = Resources.Loading
            val result = repository.signup(email, pass, userName)
            _signUpResponse.value = result
        }
    }

    fun login(email: String,pass:String){
        viewModelScope.launch {
            _loginResponse.value = Resources.Loading
            val result = repository.login(email,pass)
            _loginResponse.value = result
        }
    }

    fun setUserInfoDatabase(userId:String,userName:String){
        viewModelScope.launch {
            _isUserInfoSave.value = Resources.Loading
            val result = repository.setUserInfoDatabase(userId,userName)
            _isUserInfoSave.value = result
        }
    }

    fun logout() {
        repository.logout()
        _loginResponse.value = null
        _signUpResponse.value = null
    }
}