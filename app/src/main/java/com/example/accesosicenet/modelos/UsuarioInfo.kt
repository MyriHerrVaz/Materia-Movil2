package com.example.accesosicenet.modelos

data class UsuarioInfo(
    val fechaReins: String,
    val modEducativo:Int,
    val adeudo: Boolean,
    val urlFoto: String,
    val adeudoDescripcion: String,
    val inscrito:Boolean,
    val estatus: String,
    val semActual: Int,
    val cdtosAcumulados: Int,
    val cdtosActuales: Int,
    val especialidad: String,
    val carrera:String,
    val lineamiento: Int,
    val nombre: String,
    val matricula: String
)

