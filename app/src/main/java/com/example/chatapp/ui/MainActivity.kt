package com.example.chatapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.ui.chat.ChatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {
    override val mViewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var navController: NavController
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        if(mViewModel.currentUser != null){
            redirectChatActivity()
        }
    }

    private fun redirectChatActivity() {
        startActivity(Intent(this@MainActivity,ChatActivity::class.java))
    }

    override fun onBackPressListener() {

    }

    override fun setActionBarTitle(title: String?) {
        mViewBinding.actionBar.tvTitle.text = title
    }

    override fun setActionBar() {
        mViewBinding.actionBar.tvTitle.visibility = View.VISIBLE
    }

    override fun hideActionBar() {
        mViewBinding.actionBar.tvTitle.visibility = View.GONE
    }
}