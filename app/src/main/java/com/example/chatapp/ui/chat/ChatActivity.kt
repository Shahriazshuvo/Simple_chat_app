package com.example.chatapp.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding>() {
    override val mViewModel: ChatViewModel by viewModels()

    override fun getViewBinding():ActivityChatBinding = ActivityChatBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }

    override fun onBackPressListener() {

    }

    override fun setActionBarTitle(title: String?) {

    }

    override fun setActionBar() {

    }

    override fun hideActionBar() {

    }

}