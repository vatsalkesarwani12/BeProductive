package com.vatsal.kesarwani.profile.ui.editProfile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vatsal.kesarwani.core.loadingDialog.ViewDialog
import com.vatsal.kesarwani.core.viewmodelfactory.ViewModelProviderFactory
import com.vatsal.kesarwani.profile.R
import com.vatsal.kesarwani.profile.databinding.ActivityEditProfileBinding
import com.vatsal.kesarwani.profile.navigation.ProfileNavigation
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class EditProfileActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, EditProfileActivity::class.java))
        }
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProviderFactory

    lateinit var viewModel : EditProfileViewModel

    private lateinit var viewBinding : ActivityEditProfileBinding

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var loadingDialog : ViewDialog

    private val navigation : ProfileNavigation = XInjectionManager.findComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)
        compositeDisposable = CompositeDisposable()
        loadingDialog = ViewDialog(this)
    }

}