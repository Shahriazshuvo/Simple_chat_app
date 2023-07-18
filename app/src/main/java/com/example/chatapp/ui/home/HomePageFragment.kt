package com.example.chatapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentHomePageBinding
import com.example.chatapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : BaseFragment<HomeViewModel,FragmentHomePageBinding>() {
    override val mViewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): FragmentHomePageBinding = FragmentHomePageBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
        currentUserObserver()
    }

    private fun currentUserObserver() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val reference =
            firebaseUser?.uid?.let { FirebaseDatabase.getInstance().getReference("Users").child(it) }
        reference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setOnClickListener() {
        mViewBinding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_loginFragment)
        }

        mViewBinding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_signUpFragment)
        }
    }

    private fun initView() {
        val activity = requireActivity()
        if(activity is MainActivity){
            activity.setActionBar()
            activity.setActionBarTitle("Home")
        }
        mViewBinding.btnLogin.isEnabled = true
        mViewBinding.btnRegister.isEnabled = true
    }

}