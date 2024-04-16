package com.lmamlrg.broadcastreceiverytelefonia

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lmamlrg.broadcastreceiverytelefonia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var elBroadcastReceiver : ElBroadcastReceiver? = null
    private var numero = ""
    private var mensaje = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val view = binding.root

        val intentFilter = IntentFilter()
        intentFilter.addAction((TelephonyManager.ACTION_PHONE_STATE_CHANGED))
        elBroadcastReceiver = ElBroadcastReceiver()
        registerReceiver(elBroadcastReceiver, intentFilter)
        binding.buttonSave.setOnClickListener{
            numero = binding.editNumeroTelefono.text.toString()
            mensaje = binding.editTextMessage.text.toString()

            val intent = Intent(this, ServicerReceiver::class.java)
            startService(intent)

            val sharedPreferences = getSharedPreferences( "Preferencias", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("Numero:", numero)
            editor.putString("Mensaje:", mensaje)
            editor.apply()
        }
        elBroadcastReceiver= ElBroadcastReceiver()
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter()
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(elBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        elBroadcastReceiver?.let{
            unregisterReceiver(it)
        }
    }


}

