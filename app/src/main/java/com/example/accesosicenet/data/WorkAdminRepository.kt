package com.example.accesosicenet.data

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.accesosicenet.data.Workers.WorkerLogin
import com.example.accesosicenet.data.Workers.workerBD.workerCardex1BD
import com.example.accesosicenet.data.Workers.workerBD.workerCardex2BD
import com.example.accesosicenet.data.Workers.workerBD.workerCardexResumenBD
import com.example.accesosicenet.data.Workers.workerBD.workerCargaAcademicaBD
import com.example.accesosicenet.data.Workers.workerBD.workerFinalBD
import com.example.accesosicenet.data.Workers.workerBD.workerLoginBD
import com.example.accesosicenet.data.Workers.workerBD.workerUnidadBD
import com.example.accesosicenet.data.Workers.workerCardex1
import com.example.accesosicenet.data.Workers.workerCardex2
import com.example.accesosicenet.data.Workers.workerCardexResumen
import com.example.accesosicenet.data.Workers.workerCargaAcademica
import com.example.accesosicenet.data.Workers.workerFinal
import com.example.accesosicenet.data.Workers.workerUnidad
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class WorkAdminRepository(context: Context): usuarioRepositoryWorker {
    private val workManager = WorkManager.getInstance(context)
    override fun getAccess(matricula: String, password: String) {
        var data = workDataOf(
            "matricula" to matricula,
            "password" to password
        )
        val workerLogin = OneTimeWorkRequestBuilder<WorkerLogin>()
            .setInputData(data)
            .addTag("workerLogin")
            .build()

        val workerLoginBD = OneTimeWorkRequestBuilder<workerLoginBD>()
            .addTag("workerLoginBD")
            .build()

        workManager.beginUniqueWork("workersLogin",
            ExistingWorkPolicy.APPEND,
            workerLogin)
            .then(workerLoginBD)
            .enqueue()
    }
    override fun getCargaAcademica() {
        val workerCarga = OneTimeWorkRequestBuilder<workerCargaAcademica>()
            .addTag("workerCarga")
            .build()

        val workerCargaBD = OneTimeWorkRequestBuilder<workerCargaAcademicaBD>()
            .addTag("workerCargaBD")
            .build()

        workManager.beginUniqueWork("workersCarga",
            ExistingWorkPolicy.REPLACE,
            workerCarga)
            .then(workerCargaBD)
            .enqueue()
    }
    override fun getCardex(lineamiento: String) {
        var data = workDataOf(
            "lineamiento" to lineamiento
        )

        val workerCardex1 = OneTimeWorkRequestBuilder<workerCardex1>()
            .setInputData(data)
            .addTag("workerCardex1")
            .build()

        val workerCardex1BD = OneTimeWorkRequestBuilder<workerCardex1BD>()
            .addTag("workerCardex1BD")
            .build()

        workManager.beginUniqueWork("workersCardex1BD",
            ExistingWorkPolicy.REPLACE,
            workerCardex1)
            .then(workerCardex1BD)
            .enqueue()
    }
    override fun getCardex2(lineamiento: String) {
        var data = workDataOf(
            "lineamiento" to lineamiento
        )

        val workerCardex2 = OneTimeWorkRequestBuilder<workerCardex2>()
            .setInputData(data)
            .addTag("workerCardex2")
            .build()

        val workerCardex2BD = OneTimeWorkRequestBuilder<workerCardex2BD>()
            .addTag("workerCardex2BD")
            .build()

        workManager.beginUniqueWork("workersCardex2",
            ExistingWorkPolicy.REPLACE,
            workerCardex2)
            .then(workerCardex2BD)
            .enqueue()
    }
    override fun getCardexR(lineamiento: String) {
        var data = workDataOf(
            "lineamiento" to lineamiento
        )

        val workerCardexR = OneTimeWorkRequestBuilder<workerCardexResumen>()
            .setInputData(data)
            .addTag("workerCardexR")
            .build()

        val workerCardexRBD = OneTimeWorkRequestBuilder<workerCardexResumenBD>()
            .addTag("workerCardexBD")
            .build()

        workManager.beginUniqueWork("workersCardexR",
            ExistingWorkPolicy.REPLACE,
            workerCardexR)
            .then(workerCardexRBD)
            .enqueue()
    }

    override fun getCalificacionUnidad() {

        val workerUnidad = OneTimeWorkRequestBuilder<workerUnidad>()
            .addTag("workerUnidad")
            .build()

        val workerUnidadBD = OneTimeWorkRequestBuilder<workerUnidadBD>()
            .addTag("workerUnidadBD")
            .build()

        workManager.beginUniqueWork("workersUnidades",
            ExistingWorkPolicy.REPLACE,
            workerUnidad)
            .then(workerUnidadBD)
            .enqueue()
    }
    override fun getCalificacionFinal(modoEducativo: String) {
        var data = workDataOf(
            "mdoEducativo" to modoEducativo
        )

        val workerFinal = OneTimeWorkRequestBuilder<workerFinal>()
            .setInputData(data)
            .addTag("workerFinal")
            .build()

        val workerFinalBD = OneTimeWorkRequestBuilder<workerFinalBD>()
            .addTag("workerFinalBD")
            .build()

        workManager.beginUniqueWork("workersFinales",
            ExistingWorkPolicy.REPLACE,
            workerFinal)
            .then(workerFinalBD)
            .enqueue()
    }

    override val WorkUsuarioInfo: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerLogin").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val WorkFinales: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerFinal").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val WorkUnidades: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerUnidad").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }
    override val WorkCargaCademica: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerCarga").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }
    override val WorkCardex: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerCardex1").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val WorkCardex2: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerCardex2").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val WorkCardexR: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("workerCardexR").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

}