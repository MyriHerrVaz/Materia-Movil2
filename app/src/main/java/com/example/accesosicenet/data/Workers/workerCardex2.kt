package com.example.accesosicenet.data.Workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.accesosicenet.UsuarioApplication

class workerCardex2 (ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepository
    override suspend fun doWork(): Result {
        val lineamiento = inputData.getString("lineamiento")

        if (!lineamiento.isNullOrEmpty()){
            var Cardex = contexto.getCardex(lineamiento).split("[","]")
            var CardexM=Cardex.get(1).split("},{")
            var jsonCardex2="["
            for (i in CardexM.size/2..CardexM.size-1){
                if(i==(CardexM.size-1)){
                    jsonCardex2+="{"+CardexM.get(i)+"]"
                }else{
                    jsonCardex2+="{"+CardexM.get(i)+"},"
                }
            }
            var GuardarCardexParte2 = workDataOf("Cardexparte2" to jsonCardex2)

            return Result.success(GuardarCardexParte2)
        }
        return Result.failure()

    }
}