package com.vatsal.kesarwani.login.ui.fragment.otp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vatsal.kesarwani.core.extensions.isValid
import com.vatsal.kesarwani.core.utils.SharedPrefUtil
import com.vatsal.kesarwani.core.utils.lifecycle.SingleLiveEvent
import com.vatsal.kesarwani.login.data.LoginRepository
import com.vatsal.kesarwani.login.data.request.EmailOtpRequest
import com.vatsal.kesarwani.login.data.request.LoginRequest
import com.vatsal.kesarwani.login.ui.fragment.otp.OtpViewState.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OtpViewModel @Inject constructor(
        private val app : Application,
        private val sharedPrefUtil: SharedPrefUtil,
        private val loginRepository: LoginRepository
) : AndroidViewModel(app) {

    private var viewState : OtpViewState = Init
        set(value) {
            field = value
            stateObservable.postValue(value)
        }

    val stateObservable: SingleLiveEvent<OtpViewState> by lazy {
        SingleLiveEvent()
    }

    var otp : MutableLiveData<String> = MutableLiveData()

    var emailOtpRequest = EmailOtpRequest()

    fun onClick() {
        if(otp.value.isValid()){
            emailOtpRequest.otp = Integer.parseInt(otp.value!!)
            emailOtpRequest.email = sharedPrefUtil.loginEmail
            viewState = Verify
        }else {
            viewState = OnFieldError("Enter the correct otp")
        }
    }

    fun resendOtp() {
        viewState = ResendOtp
    }

    fun verifyMobileOtp() : Disposable {
        viewState = Loading
        return loginRepository.mobileOtp(emailOtpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState = OnSuccess(it)
                },{
                    viewState = OnError(it.message)
                })
    }

    fun login(loginRequest : LoginRequest) : Disposable {
        viewState = Loading
        return loginRepository.login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState = OnSuccessResend(it.status)
                },{
                    viewState = OnError(it.message)
                })
    }

}