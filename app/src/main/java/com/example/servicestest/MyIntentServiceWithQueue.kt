package com.example.servicestest

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyIntentServiceWithQueue : IntentService(INTENT_SERVICE_NAME) {

    override fun onCreate() {
        super.onCreate()
        setIntentRedelivery(true)
        log("onCreate: $this")
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent: $this")
        val page = intent?.getIntExtra(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy $this")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyIntentServiceWithQueue: $message")
    }

    companion object {
        private const val INTENT_SERVICE_NAME = "intent_service_name"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentServiceWithQueue::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}