package com.example.accesosicenet.data.Workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.accesosicenet.UsuarioApplication

class workerUnidad(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepository
    override suspend fun doWork(): Result {
        try {
            var unidades = contexto.getCalificacionUnidad()
            var salidaUnidad = workDataOf("Unidades" to unidades)
            return Result.success(salidaUnidad)
        }catch (e:Exception) {
            return Result.failure()
        }
        return Result.failure()

    }
}