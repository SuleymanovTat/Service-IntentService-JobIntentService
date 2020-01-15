package ru.suleymanovtat.jobintentserviceapplication.service

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import ru.suleymanovtat.jobintentserviceapplication.activity.NextActivity


class JobSchedulerService : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        Log.e("my", "onStartJob")
        addNotification()
        Log.e("my", "NotificationCompat")
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Log.e("my", "onStopJob")
        return false
    }

    private fun addNotification() {
        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_delete)
            .setContentTitle("Notifications Example")
            .setContentText("This is a test notification")
            .setDefaults(Notification.DEFAULT_SOUND)

        val notificationIntent = Intent(this, NextActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(contentIntent)
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }
}
