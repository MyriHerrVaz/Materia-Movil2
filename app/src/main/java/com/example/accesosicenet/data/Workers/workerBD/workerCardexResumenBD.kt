package com.example.accesosicenet.data.Workers.workerBD

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.TablaCardexR
import com.google.gson.Gson

class workerCardexResumenBD(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepositoryBD
    override suspend fun doWork(): Result {
        var lineamientoR = inputData.getString("CardexResumen")

        if (!lineamientoR.isNullOrEmpty()){
            var CardexResumen = Gson().fromJson(lineamientoR, TablaCardexR::class.java)
            contexto.deleteCardexR()
            contexto.insertCardexR(CardexResumen)
            return Result.success()
        }
        return Result.failure()
        }

    }