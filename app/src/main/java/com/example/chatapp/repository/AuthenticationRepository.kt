package com.example.chatapp.repository

import android.app.Application
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.data.Resources
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository{
    val currentUser:FirebaseUser?
   suspend fun login(email:String, password:String):Resources<FirebaseUser>
   suspend fun signup(email:String,password: String,userName:String):Resources<FirebaseUser>
   suspend fun setUserInfoDatabase(userId:String,userName:String):Resources<Void>
   fun logout()
}