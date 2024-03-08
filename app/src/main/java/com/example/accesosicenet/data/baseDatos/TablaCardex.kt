package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TablaCardex")
data class TablaCardex(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "A1") var A1: String = "",
    @ColumnInfo(name = "A2") var A2: Any? = null,
    @ColumnInfo(name = "A3") var A3: Any? = null,
    @ColumnInfo(name = "Acred") var Acred: String = "",
    @ColumnInfo(name = "Calif") var Calif: Int = 0,
    @ColumnInfo(name = "Cdts") var Cdts: Int = 0,
    @ColumnInfo(name = "ClvMat") var ClvMat: String = "",
    @ColumnInfo(name = "ClvOfiMat") var ClvOfiMat: String = "",
    @ColumnInfo(name = "Materia") var Materia: String = "",
    @ColumnInfo(name = "P1") var P1: String = "",
    @ColumnInfo(name = "P2") var P2: Any? = null,
    @ColumnInfo(name = "P3") var P3: Any? = null,
    @ColumnInfo(name = "S1") var S1: String = "",
    @ColumnInfo(name = "S2") var S2: Any? = null,
    @ColumnInfo(name = "S3") var S3: Any? = null
)
