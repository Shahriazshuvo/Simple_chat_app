package com.example.chatapp.ui

import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AuthenticationRepository):BaseViewModel() {
    val currentUser: FirebaseUser?
        get() = repository.currentUser
}