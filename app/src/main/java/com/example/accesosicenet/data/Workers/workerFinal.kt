package com.example.accesosicenet.data.Workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.accesosicenet.UsuarioApplication

class workerFinal (ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepository
    override suspend fun doWork(): ListenableWorker.Result {
        val modEducativo = inputData.getString("mdoEducativo")

        if (!modEducativo.isNullOrEmpty()){
            var finales  = contexto.getCalificacionFinal(modEducativo)
            var GuardarFinales = workDataOf("finales" to finales)

            return ListenableWorker.Result.success(GuardarFinales)
        }
        return ListenableWorker.Result.failure()

    }
}