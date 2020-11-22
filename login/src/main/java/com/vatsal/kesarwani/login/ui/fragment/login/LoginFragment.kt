package com.vatsal.kesarwani.login.ui.fragment.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vatsal.kesarwani.core.extensions.showToast
import com.vatsal.kesarwani.core.loadingDialog.ViewDialog
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelProviderFactory
import com.vatsal.kesarwani.login.databinding.FragmentLoginBinding
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import com.vatsal.kesarwani.login.ui.fragment.login.LoginViewState.*
import javax.inject.Inject

class LoginFragment : Fragment() {
    
    @Inject
    lateinit var viewModelFactory : ViewModelProviderFactory

    lateinit var viewModel : LoginViewModel

    private lateinit var viewBinding : FragmentLoginBinding

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
    ): View? {
        viewBinding = FragmentLoginBinding.inflate(inflater)
        initUI()
        observeRendering()
        return viewBinding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        compositeDisposable = CompositeDisposable()
        loadingDialog = ViewDialog(requireContext())
    }

    private fun initUI() {

    }

    private fun observeRendering() {
        viewModel.stateObservable.observe(this, {
            render(it)
        })
    }

    private fun render(state : LoginViewState) {
        when(state) {
            Loading -> showLoading()
            OnSuccess -> onSuccess()
            is OnError -> showToast(state.mssg)
            is OnFieldError -> viewBinding.etEmail.error = state.mssg
        }
    }

    private fun showLoading() {
        loadingDialog.showDialog()
    }

    private fun hideLoading() {
        loadingDialog.hideDialog()
    }

    private fun onSuccess() {
        hideLoading()


    }

    override fun onStop() {
        super.onStop()
        hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}