package com.example.accesosicenet.data.Workers.workerBD

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.accesosicenet.UsuarioApplication
import com.example.accesosicenet.data.baseDatos.TablaCargaAcademica
import com.example.accesosicenet.data.baseDatos.TablaUnidad
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class workerUnidadBD(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepositoryBD

    override suspend fun doWork(): Result {
        var Unidades = inputData.getString("Unidades")

        if (!Unidades.isNullOrEmpty()) {
            var type = object : TypeToken<List<TablaUnidad>>() {}.type
            var uni: List<TablaUnidad> = Gson().fromJson(Unidades, type)
            contexto.deleteUnidades()
            uni.forEach {
                if (it.C1 == null)
                    it.C1 = ""
                if (it.C2 == null)
                    it.C2 = ""
                if (it.C3 == null)
                    it.C3 = ""
                if (it.C4 == null)
                    it.C4 = ""
                if (it.C5 == null)
                    it.C5 = ""
                if (it.C6 == null)
                    it.C6 = ""
                if (it.C7 == null)
                    it.C7 = ""
                if (it.C8 == null)
                    it.C8 = ""
                if (it.C9 == null)
                    it.C9 = ""
                if (it.C10 == null)
                    it.C10 = ""
                if (it.C11 == null)
                    it.C11 = ""
                if (it.C12 == null)
                    it.C12 = ""
                if (it.C13 == null)
                    it.C13 = ""
                contexto.insertUnidades(it)
            }
            return Result.success()
        }
        return Result.failure()
    }
}