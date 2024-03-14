package com.example.accesosicenet.data.Workers.workerBD

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.TablaCargaAcademica
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class workerCargaAcademicaBD(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepositoryBD

    override suspend fun doWork(): Result {
        var Horario=inputData.getString("CargaAcademica")

        if(!Horario.isNullOrEmpty()){
            var type = object : TypeToken<List<TablaCargaAcademica>>() {}.type
            var horario: List<TablaCargaAcademica> = Gson().fromJson(Horario, type)
            contexto.deleteHorario()
            horario.forEach {
                contexto.insertHorario(it)
            }
            return Result.success()
        }
        return  Result.failure()
    }
}