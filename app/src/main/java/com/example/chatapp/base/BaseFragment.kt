package com.example.chatapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VM : BaseViewModel, VB: ViewBinding>: Fragment() {
    protected abstract val mViewModel: VM
    protected lateinit var mViewBinding: VB

    abstract fun getViewBinding():VB
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fm = requireActivity().supportFragmentManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        return mViewBinding.root
    }

    fun showSnackbar(message: String) {
        if (message.isNotEmpty())
            Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).setTextMaxLines(15).show()
    }

    fun showToast(message: String) {
        if (message.isNotEmpty())
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}