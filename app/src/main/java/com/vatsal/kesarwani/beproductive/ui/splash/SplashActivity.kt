package com.vatsal.kesarwani.beproductive.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.vatsal.kesarwani.beproductive.R
import com.vatsal.kesarwani.beproductive.ui.MainActivity
import com.vatsal.kesarwani.core.utils.SharedPrefUtil
import com.vatsal.kesarwani.login.ui.AuthActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY_TIME = 2000L
        fun start(context: Context) {
            context.startActivity(Intent(context, SplashActivity::class.java))
        }
    }

    @Inject
    lateinit var sharedPrefUtil: SharedPrefUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            checkIfUserLoggedIn()
        }, SPLASH_DELAY_TIME)
    }

    private fun checkIfUserLoggedIn() {
        if (sharedPrefUtil.isLoggedIn.not()) {
            AuthActivity.start(this)
        } else {
            MainActivity.start(this)
        }
        finish()
    }
}