package com.example.accesosicenet.data.Workers.workerBD

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.TablaCardex
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class workerCardex2BD(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepositoryBD
    override suspend fun doWork(): Result {
        val lineamiento2 = inputData.getString("Cardexparte2")

        if (!lineamiento2.isNullOrEmpty()){
            var type = object : TypeToken<List<TablaCardex>>() {}.type
            var kardex: List<TablaCardex> = Gson().fromJson(lineamiento2, type)
            kardex.forEach {
                if(it.A1==null)
                    it.A1=""
                if(it.A2==null)
                    it.A2=""
                if(it.A3==null)
                    it.A3=""
                if(it.P1==null)
                    it.P1=""
                if(it.P2==null)
                    it.P2=""
                if(it.P3==null)
                    it.P3=""
                if(it.S1==null)
                    it.S1=""
                if(it.S2==null)
                    it.S2=""
                if(it.S3==null)
                    it.S3=""
                contexto.insertCardex(it)
            }
            return Result.success()
        }
        return Result.failure()

    }
}