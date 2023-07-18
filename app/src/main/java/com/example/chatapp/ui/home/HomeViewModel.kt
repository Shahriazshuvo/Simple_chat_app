package com.example.chatapp.ui.home

import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AuthenticationRepository):BaseViewModel() {

    val currentUser: FirebaseUser?
        get() = repository.currentUser
}