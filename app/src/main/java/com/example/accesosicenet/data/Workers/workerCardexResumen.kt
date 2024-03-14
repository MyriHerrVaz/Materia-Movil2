package com.example.accesosicenet.data.Workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.accesosicenet.UsuarioApplication

class workerCardexResumen (ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
        var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepository
        override suspend fun doWork(): Result {
            val lineamiento = inputData.getString("lineamiento")

            if (!lineamiento.isNullOrEmpty()){
                var Cardex = contexto.getCardex(lineamiento).split("[","]")
                var CardexResumen="{"+Cardex.get(2).split("{","}").get(1)+"}"
                var GuardarCardexResumen = workDataOf("CardexResumen" to CardexResumen)

                return Result.success(GuardarCardexResumen)
            }
            return Result.failure()

        }
}