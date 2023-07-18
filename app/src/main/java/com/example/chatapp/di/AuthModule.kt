package com.example.chatapp.di

import com.example.chatapp.repository.AuthenticationRepository
import com.example.chatapp.repository.AuthenticationRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {
    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseDatabase():FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthenticationRepositoryImpl):AuthenticationRepository = impl
}