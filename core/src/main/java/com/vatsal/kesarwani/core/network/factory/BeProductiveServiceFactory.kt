package com.vatsal.kesarwani.core.network.factory

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.vatsal.kesarwani.core.BuildConfig
import com.vatsal.kesarwani.core.network.interceptor.AuthorizationInterceptor
import java.util.concurrent.TimeUnit
import okhttp3.Dns
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BeProductiveServiceFactory(val application: Application) {

    fun create(): Retrofit {
        val client = createOkHttp()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    private fun createOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .dns(Dns.SYSTEM)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            //.addInterceptor(AuthorizationInterceptor(application))

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }

        return builder.build()
    }

    companion object {
        private const val OK_HTTP_TIMEOUT = 60L
    }
}
