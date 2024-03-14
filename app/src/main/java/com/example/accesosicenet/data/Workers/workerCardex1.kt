package com.example.accesosicenet.data.Workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.accesosicenet.UsuarioApplication

class workerCardex1  (ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    var contexto=(ctx.applicationContext as UsuarioApplication).container.usuariosRepository
    override suspend fun doWork(): Result {
        val lineamiento = inputData.getString("lineamiento")

        if (!lineamiento.isNullOrEmpty()){
            var Cardex = contexto.getCardex(lineamiento).split("[","]")
            var CardexM=Cardex.get(1).split("},{")
            var jsonCardex1="["
            for (i in 0..CardexM.size/2-1){
                if(i==0){
                    jsonCardex1+=CardexM.get(i)+"},"
                }else{
                    if(i==((CardexM.size/2)-1)){
                        jsonCardex1+="{"+CardexM.get(i)+"}]"
                    }else{
                        jsonCardex1+="{"+CardexM.get(i)+"},"
                    }
                }
            }
            var GuardarCardexParte1 = workDataOf("Cardexparte1" to jsonCardex1)

            return Result.success(GuardarCardexParte1)
        }
        return Result.failure()
    }
}