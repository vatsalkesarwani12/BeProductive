package com.vatsal.kesarwani.login.ui.fragment.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vatsal.kesarwani.core.extensions.isEmail
import com.vatsal.kesarwani.core.utils.lifecycle.SingleLiveEvent
import com.vatsal.kesarwani.login.data.LoginRepository
import com.vatsal.kesarwani.login.data.request.LoginRequest
import com.vatsal.kesarwani.login.ui.fragment.login.LoginViewState.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Error
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val app : Application,
    private val loginRepository: LoginRepository
) : AndroidViewModel(app) {

    private var viewState : LoginViewState = Init
        set(value) {
            field = value
            stateObservable.postValue(value)
        }

    val stateObservable: SingleLiveEvent<LoginViewState> by lazy {
        SingleLiveEvent()
    }

    var email : MutableLiveData<String> = MutableLiveData()

    var loginRequest = LoginRequest()

    fun onClick(){
        if(email.value.isEmail()){
            loginRequest.email = email.value
            viewState = DoLogin
        }else{
            viewState = OnFieldError("Enter valid email address")
        }
    }

    fun login() : Disposable {
        viewState = Loading
        return loginRepository.login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState = OnSuccess(it)
                },{
                    viewState = OnError(it.message)
                })
    }

}