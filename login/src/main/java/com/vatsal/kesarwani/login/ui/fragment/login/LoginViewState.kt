package com.vatsal.kesarwani.login.ui.fragment.login

sealed class LoginViewState {

    object Init : LoginViewState()

    object Loading : LoginViewState()

    object OnSuccess : LoginViewState()

    data class OnError(var mssg : String?) : LoginViewState()

    data class OnFieldError(var mssg : String?) : LoginViewState()

}