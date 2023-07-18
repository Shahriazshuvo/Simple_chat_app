package com.example.chatapp.repository

import com.example.chatapp.data.Resources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val firebaseAuth:FirebaseAuth,private val firebaseDatabase: FirebaseDatabase) : AuthenticationRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resources<FirebaseUser> {
        return try{
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Resources.Success(result.user!!)
        }catch (e:Exception){
            e.printStackTrace()
            Resources.Failure(e)
        }
    }

    override suspend fun signup(email: String, password: String,userName:String): Resources<FirebaseUser> {
        return try{
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            Resources.Success(result.user!!)
        }catch (e:Exception){
            e.printStackTrace()
            Resources.Failure(e)
        }
    }

    override suspend fun setUserInfoDatabase(userId:String,userName:String):Resources<Void> {
       val reference = firebaseDatabase.getReference("Users").child(userId)
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["id"] = userId
        hashMap["username"] = userName
        hashMap["imageURL"] = "default"
        return try{
            val result = reference.setValue(hashMap).await()
            Resources.Success(result)
        }catch (e:Exception){
            e.printStackTrace()
            Resources.Failure(e)
        }


    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}