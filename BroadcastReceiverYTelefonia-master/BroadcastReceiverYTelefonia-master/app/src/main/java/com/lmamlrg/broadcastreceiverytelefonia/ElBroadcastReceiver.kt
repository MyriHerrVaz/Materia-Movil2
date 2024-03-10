package com.lmamlrg.broadcastreceiverytelefonia

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.*
import android.util.Log

class ElBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val estado = intent?.getStringArrayExtra(TelephonyManager.EXTRA_STATE)
        if(estado!!.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            val phoneNumber= intent?.getStringExtra(EXTRA_INCOMING_NUMBER)
            val sharedPreferences = context?.getSharedPreferences("myPrefs",Context.MODE_PRIVATE)
            val savedPhoneNumber = sharedPreferences?.getString("phoneNumber" ,"")
            val savedMessage = sharedPreferences?.getString("message", "")
            if (phoneNumber == savedPhoneNumber) {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, savedMessage, null, null)
                Log.i("Respuesta","Mensaje enviado correctamnete")
            }else{
                Log.i("Respuesta","Hubo un problema al enviar el mensaje")
            }
        }
    }
}