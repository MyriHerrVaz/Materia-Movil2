package com.lmamlrg.broadcastreceiverytelefonia

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.telephony.TelephonyManager

class ServicerReceiver : Service() {
    private lateinit var callReceiver: ElBroadcastReceiver
    private var isRunning =false
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags:Int, startID:Int) : Int{
        if (isRunning){
            stopService(Intent(applicationContext, ServicerReceiver::class.java))
        }

        isRunning=true
        callReceiver = ElBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(callReceiver, intentFilter)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(callReceiver)
        isRunning=false
    }

}