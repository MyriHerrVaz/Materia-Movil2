package com.example.accesosicenet

import android.app.Application
import com.example.accesosicenet.data.AppContainer
import com.example.accesosicenet.data.DefaultAppContainer

class UsuarioApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}