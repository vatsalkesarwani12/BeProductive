package com.vatsal.kesarwani.login.ui.fragment.otp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.vatsal.kesarwani.core.extensions.showSnackBar
import com.vatsal.kesarwani.core.extensions.showToast
import com.vatsal.kesarwani.core.loadingDialog.ViewDialog
import com.vatsal.kesarwani.core.utils.SharedPrefUtil
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelProviderFactory
import com.vatsal.kesarwani.login.data.request.LoginRequest
import com.vatsal.kesarwani.login.data.response.EmailOtpResponse
import com.vatsal.kesarwani.login.databinding.FragmentOtpBinding
import com.vatsal.kesarwani.login.ui.AuthViewModel
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import com.vatsal.kesarwani.login.ui.fragment.otp.OtpViewState.*
import com.vatsal.kesarwani.login.utils.VerifyType
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class OtpFragment : Fragment() {

    companion object {
        private const val ARGS_VERIFY_TYPE = "args_verify_type"
        private const val ARGS_DATA = "args_verify_data" // mobileNo or Email
        fun createBundle(otpVerifyType: VerifyType, data: String): Bundle {
            return Bundle().apply {
                putSerializable(ARGS_VERIFY_TYPE, otpVerifyType)
                putString(ARGS_DATA, data)
            }
        }
    }

    @Inject
    lateinit var sharedPrefUtil: SharedPrefUtil

    @Inject
    lateinit var viewModelFactory : ViewModelProviderFactory

    private val activityViewModel: AuthViewModel by activityViewModels()

    private lateinit var otpType: VerifyType

    private lateinit var data: String

    lateinit var viewModel: OtpViewModel

    private lateinit var viewBinding : FragmentOtpBinding

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var loadingDialog : ViewDialog

    private var loginRequest = LoginRequest()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        handleArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOtpBinding.inflate(inflater)
        initUI()
        fieldObserve()
        observeRendering()
        return viewBinding.root
    }

    private fun handleArguments() {
        otpType = arguments?.getSerializable(ARGS_VERIFY_TYPE) as VerifyType
        data = arguments?.getString(ARGS_DATA)!!
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(OtpViewModel::class.java)
        compositeDisposable = CompositeDisposable()
        loadingDialog = ViewDialog(requireContext())
    }

    private fun initUI() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner

        loginRequest.email = sharedPrefUtil.loginEmail
    }

    private fun fieldObserve() {
        viewModel.otp.observe(viewLifecycleOwner , {
            viewBinding.etOtp.error = null
        })
    }

    private fun observeRendering() {
       viewModel.stateObservable.observe(this, {
            render(it)
        })
    }

    private fun render(state : OtpViewState) {
       when(state) {
           Loading -> showLoading()
           Verify -> verifyOtp()
           ResendOtp -> resendOtp()
           is OnSuccessResend -> {
               hideLoading()
               showToast(state.mssg)
           }
           is OnSuccess -> onSuccess(state.emailOtpResponse)
           is OnError -> showToast(state.mssg)
           is OnFieldError -> viewBinding.etOtp.error = state.mssg
        }
    }

    private fun showLoading(){
        loadingDialog.showDialog()
    }

    private fun hideLoading() {
        loadingDialog.hideDialog()
    }

    private fun resendOtp() {
        addDisposable(
            viewModel.login(loginRequest)
        )
    }

    private fun verifyOtp() {
        addDisposable(
            viewModel.verifyMobileOtp()
        )
    }

    private fun onSuccess(emailOtpResponse: EmailOtpResponse) {
        hideLoading()

        if (emailOtpResponse.status.equals("OK")){
            sharedPrefUtil.authKey = emailOtpResponse.data?.token.toString()

            /**test */
            showToast(emailOtpResponse.data?.accStatus.toString())

            activityViewModel.newUser()

        }else{
            showToast("Something went wrong")
        }
    }

    override fun onStop() {
        super.onStop()
        hideLoading()
    }

    private fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            compositeDisposable.add(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}