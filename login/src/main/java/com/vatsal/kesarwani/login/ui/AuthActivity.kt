package com.vatsal.kesarwani.login.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelProviderFactory
import com.vatsal.kesarwani.login.R
import com.vatsal.kesarwani.login.navigation.LoginNavigation
import dagger.android.AndroidInjection
import me.vponomarenko.injectionmanager.x.XInjectionManager
import com.vatsal.kesarwani.login.ui.AuthViewState.*
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    companion object{
        fun start(context : Context) {
            context.startActivity(Intent(context, AuthActivity::class.java))
        }
    }

    lateinit var viewModel : AuthViewModel

    @Inject
    lateinit var viewModelFactory : ViewModelProviderFactory

    private val navigation: LoginNavigation = XInjectionManager.findComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initViewModel()
        setObservers()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.stateObservable.observe(this, {
            render(it)
        })
    }

    private fun render(state: AuthViewState) {
        when(state) {
            GoToLoginScreen -> {
                getNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
            is GoToOtpScreen -> {
                getNavController().navigate(R.id.action_loginFragment_to_otpFragment)
            }
            NewUserAccount -> {

            }
            ReLoginUser -> {

            }
        }
    }

    private fun getNavController(): NavController {
        return findNavController(R.id.nav_host_fragment)
    }

}