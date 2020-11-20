package com.vatsal.kesarwani.beproductive.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vatsal.kesarwani.beproductive.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    companion object{
        fun start(context : Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}