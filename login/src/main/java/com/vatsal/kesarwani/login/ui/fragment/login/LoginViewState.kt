package com.vatsal.kesarwani.login.ui.fragment.login

import com.vatsal.kesarwani.login.data.response.LoginResponse

sealed class LoginViewState {

    object Init : LoginViewState()

    object Loading : LoginViewState()

    object DoLogin : LoginViewState()

    data class OnSuccess(var loginResponse: LoginResponse) : LoginViewState()

    data class OnError(var mssg : String?) : LoginViewState()

    data class OnFieldError(var mssg : String?) : LoginViewState()

}