package com.vatsal.kesarwani.login.ui.fragment.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vatsal.kesarwani.core.utils.lifecycle.SingleLiveEvent
import com.vatsal.kesarwani.login.ui.fragment.login.LoginViewState.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val app : Application
) : AndroidViewModel(app) {

    private var viewState = Init
        set(value) {
            field = value
            stateObservable.postValue(value)
        }

    val stateObservable: SingleLiveEvent<LoginViewState> by lazy {
        SingleLiveEvent()
    }

    var email : MutableLiveData<String> = MutableLiveData()

}