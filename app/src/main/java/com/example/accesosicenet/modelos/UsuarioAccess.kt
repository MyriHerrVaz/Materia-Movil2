package com.example.accesosicenet.modelos

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioAccess (
    val acceso: Boolean,
    val estatus: String,
    val tipoUsuario: Int,
    val contrasenia: String,
    val matricula: String
)