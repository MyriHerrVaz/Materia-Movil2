package com.example.accesosicenet.data.Workers.workerBD

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.TablaUsuarioAccess
import com.example.accesosicenet.data.baseDatos.TablaUsuarioInfo
import com.google.gson.Gson

class workerLoginBD (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepositoryBD

    override suspend fun doWork(): Result {
        var Acceso=inputData.getString("Access")
        var Info=inputData.getString("Info")
        var Password=inputData.getString("password")

        if(!Acceso.isNullOrEmpty() && !Info.isNullOrEmpty()){
            var Access= Gson().fromJson(Acceso, TablaUsuarioAccess::class.java)
            Access.contrasenia = Password.let { it }?:""
            contexto.deleteAccess()
            contexto.insertUsuarioAccess(Access)
            var informacion= Gson().fromJson(Info, TablaUsuarioInfo::class.java)
            contexto.deleteUsuarioInformacion()
            contexto.insertUsuarioInformacion(informacion)
            return Result.success()
        }
        return  Result.failure()
    }
}