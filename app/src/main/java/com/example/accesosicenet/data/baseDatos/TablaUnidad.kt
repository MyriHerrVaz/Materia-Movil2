package com.example.accesosicenet.data.baseDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TablaUnidad")
data class TablaUnidad(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "C1") val C1: String = "",
    @ColumnInfo(name = "C10") var C10: Any? = null,
    @ColumnInfo(name = "C11") var C11: Any? = null,
    @ColumnInfo(name = "C12") var C12: Any? = null,
    @ColumnInfo(name = "C13") var C13: Any? = null,
    @ColumnInfo(name = "C2") var C2: Any? = null,
    @ColumnInfo(name = "C3") var C3: Any? = null,
    @ColumnInfo(name = "C4") var C4: Any? = null,
    @ColumnInfo(name = "C5") val C5: Any? = null,
    @ColumnInfo(name = "C6") var C6: Any? = null,
    @ColumnInfo(name = "C7") var C7: Any? = null,
    @ColumnInfo(name = "C8") var C8: Any? = null,
    @ColumnInfo(name = "C9") var C9: Any? = null,
    @ColumnInfo(name = "Grupo") var Grupo: String = "",
    @ColumnInfo(name = "Materia") var Materia: String = "",
    @ColumnInfo(name = "Observaciones") var Observaciones: String = "",
    @ColumnInfo(name = "UnidadesActivas") var UnidadesActivas: String = ""
)