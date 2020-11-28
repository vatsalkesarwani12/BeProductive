package com.vatsal.kesarwani.login.ui.fragment.started

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vatsal.kesarwani.login.databinding.FragmentStartBinding
import com.vatsal.kesarwani.login.ui.AuthViewModel
import dagger.android.support.AndroidSupportInjection

class StartFragment : Fragment() {

    private val activityViewModel: AuthViewModel by activityViewModels()

    private lateinit var viewBinding : FragmentStartBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentStartBinding.inflate(inflater)
        onClick()
        return viewBinding.root
    }

    private fun onClick() {
        viewBinding.btStart.setOnClickListener{
            startLogin()
        }
    }

    private fun startLogin() {
        activityViewModel.goToLoginScreen()
    }
}