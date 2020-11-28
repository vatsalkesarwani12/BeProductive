package com.vatsal.kesarwani.login.ui.fragment.otp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vatsal.kesarwani.core.loadingDialog.ViewDialog
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelProviderFactory
import com.vatsal.kesarwani.login.databinding.FragmentOtpBinding
import com.vatsal.kesarwani.login.ui.AuthViewModel
import com.vatsal.kesarwani.login.ui.fragment.login.LoginViewState
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class OtpFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProviderFactory

    private val activityViewModel: AuthViewModel by activityViewModels()

    private lateinit var viewBinding : FragmentOtpBinding

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var loadingDialog : ViewDialog


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOtpBinding.inflate(inflater)
        initUI()
        observeRendering()
        return viewBinding.root
    }

    private fun initViewModel() {
        //viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        compositeDisposable = CompositeDisposable()
        loadingDialog = ViewDialog(requireContext())
    }

    private fun initUI() {

    }

    private fun observeRendering() {
       /* viewModel.stateObservable.observe(this, {
            render(it)
        })*/
    }

    private fun render(state : LoginViewState) {
       /* when(state) {
            LoginViewState.Loading -> showLoading()
            LoginViewState.OnSuccess -> onSuccess()
            is LoginViewState.OnError -> showToast(state.mssg)
            is LoginViewState.OnFieldError -> showToast(state.mssg)//viewBinding.etEmail.error = state.mssg
        }*/
    }
}