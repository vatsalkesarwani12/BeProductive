package com.vatsal.kesarwani.login.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vatsal.kesarwani.core.utils.SharedPrefUtil
import com.vatsal.kesarwani.login.ui.AuthViewState.*
import com.vatsal.kesarwani.login.utils.VerifyType
import javax.inject.Inject

class AuthViewModel @Inject constructor(
        private val app : Application,
        private val sharedPrefUtil: SharedPrefUtil  //check if required else remove it
) : AndroidViewModel(app) {

    val stateObservable: MutableLiveData<AuthViewState> by lazy {
        MutableLiveData<AuthViewState>()
    }

    private fun publishState(authViewState: AuthViewState) {
        stateObservable.postValue(authViewState)
    }

    fun goToOtpScreen(data : String, type : VerifyType){
        publishState(GoToOtpScreen(data, type))
    }

    fun newUser() {
        publishState(NewUserAccount)
    }

    fun reLogin() {
        publishState(ReLoginUser)
    }

}