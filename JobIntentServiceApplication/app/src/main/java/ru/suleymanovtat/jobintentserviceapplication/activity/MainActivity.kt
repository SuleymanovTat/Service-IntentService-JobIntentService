package ru.suleymanovtat.jobintentserviceapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.suleymanovtat.jobintentserviceapplication.R
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
    }
}
