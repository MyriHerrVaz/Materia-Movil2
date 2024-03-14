package com.example.accesosicenet.data.Workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.accesosicenet.UsuarioApplication

class WorkerLogin(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepository
    override suspend fun doWork(): Result {
        val matriculaLogin = inputData.getString("matricula")
        val passwordLogin = inputData.getString("password")

        if (!matriculaLogin.isNullOrEmpty() && !passwordLogin.isNullOrEmpty()){
            var guardarAcceso = contexto.getAccess(matriculaLogin,passwordLogin)
            var guardarInfo = contexto.getInfo(matriculaLogin)
            var GuardarDatosSalida = workDataOf("Access" to guardarAcceso,"Info" to guardarInfo,"password" to passwordLogin)

            return Result.success(GuardarDatosSalida)
        }
        return Result.failure()

    }
}
