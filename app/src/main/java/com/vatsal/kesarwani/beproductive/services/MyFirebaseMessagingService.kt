package com.vatsal.kesarwani.beproductive.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.vatsal.kesarwani.beproductive.R
import com.vatsal.kesarwani.beproductive.ui.splash.SplashActivity
import com.vatsal.kesarwani.core.utils.SharedPrefUtil
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private var compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var sharedPrefUtil: SharedPrefUtil

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Timber.d(remoteMessage.data.toString())

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            val title = it.title ?: getString(R.string.app_name)
            val msgBody = it.body ?: ""
            //val imageUrl = it.imageUrl?.toString()
            sendNotification(title, msgBody)
        } ?: run {
            Timber.e("No notification payload.")
        }
    }

    override fun onNewToken(token: String) {
        if (sharedPrefUtil.isLoggedIn) {
            //compositeDisposable.add(sendRegistrationToServer(token))
            Timber.e(token)
        }
    }

    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(this, SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = getString(R.string.default_channel_id)
        val vibrate = longArrayOf(0, 400)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setVibrate(vibrate)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.default_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}