package com.example.accesosicenet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accesosicenet.data.baseDatos.TablaCardex
import com.example.accesosicenet.data.baseDatos.TablaCardexConPromedio
import com.example.accesosicenet.data.baseDatos.TablaCardexR
import com.example.accesosicenet.data.baseDatos.TablaCargaAcademica
import com.example.accesosicenet.data.baseDatos.TablaUnidad
import com.example.accesosicenet.data.baseDatos.TablaUsuarioAccess
import com.example.accesosicenet.data.baseDatos.TablaUsuarioInfo
import com.example.accesosicenet.data.baseDatos.Tablafinales
import com.example.accesosicenet.data.interfaces.DaoUsuarioInfo

@Database(
    entities = [TablaCardex::class, TablaCardexR::class, TablaCardexConPromedio::class,
               TablaCargaAcademica::class, Tablafinales::class, TablaUnidad::class,
               TablaUsuarioAccess::class, TablaUsuarioInfo::class],
    version = 1,
    exportSchema = false
)
abstract class BaseDatos : RoomDatabase() {
    abstract fun getDaoUsuarioInfo(): DaoUsuarioInfo
    companion object{
        @Volatile
        private var Instance: BaseDatos? = null
        fun getDatabase(context: Context): BaseDatos {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,BaseDatos::class.java,"SicenetBD")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}