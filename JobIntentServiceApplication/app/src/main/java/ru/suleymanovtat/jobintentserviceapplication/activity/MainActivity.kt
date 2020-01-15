package ru.suleymanovtat.jobintentserviceapplication.activity

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.suleymanovtat.jobintentserviceapplication.R
import ru.suleymanovtat.jobintentserviceapplication.service.JobSchedulerService
import ru.suleymanovtat.jobintentserviceapplication.service.SimpleIntentService
import ru.suleymanovtat.jobintentserviceapplication.service.SimpleJobIntentService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSimpleIntentService.setOnClickListener {
            SimpleIntentService.startActionBaz(
                this,
                "1",
                "2"
            )
        }
        btnSimpleJobIntentService.setOnClickListener {
            val mIntent = Intent(this, SimpleJobIntentService::class.java)
            mIntent.putExtra("maxCountValue", 1000)
            mIntent.putExtra("label", "JobIntentService")
            SimpleJobIntentService().enqueueWork(this, mIntent)
        }
        btnJobSchedulerService.setOnClickListener {
            val serviceName = ComponentName(this, JobSchedulerService::class.java)

            val jobInfo = JobInfo.Builder(344, serviceName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .setPeriodic(10_000)
                .setPersisted(true)
                .build()


            val scheduler: JobScheduler =
                getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val result = scheduler.schedule(jobInfo)
            if (result == JobScheduler.RESULT_SUCCESS) {
                Log.e("my", "RESULT_SUCCESS")
            } else {
                Log.e("my", "RESULT_FAILURE")
            }
        }
    }
}
