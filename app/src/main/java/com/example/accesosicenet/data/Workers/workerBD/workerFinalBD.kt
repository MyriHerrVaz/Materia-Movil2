package com.example.accesosicenet.data.Workers.workerBD

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.Tablafinales
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class workerFinalBD (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepositoryBD

    override suspend fun doWork(): Result {
        var Finales=inputData.getString("finales")

        if(!Finales.isNullOrEmpty()){
            var type = object : TypeToken<List<Tablafinales>>() {}.type
            var finales: List<Tablafinales> = Gson().fromJson(Finales, type)
            contexto.deleteFinal()
            finales.forEach {
                contexto.insertFinales(it)
            }
            return Result.success()
        }
        return  Result.failure()
    }
}