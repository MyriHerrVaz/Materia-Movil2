package com.example.accesosicenet.data

import ApiService
import com.example.accesosicenet.modelos.UsuarioAccess
import com.example.accesosicenet.modelos.UsuarioInfo
import com.example.accesosicenet.network.InfoApiService
import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

interface usuarioRepository{
    suspend fun getAccess(matricula: String, password: String): Boolean
    suspend fun getInfo():String
}
class UsuariosRepository (
    private val usuarioApiService: ApiService,
    private val usuarioInfo: InfoApiService
):usuarioRepository{
    override suspend fun getAccess(matricula: String, password: String ):Boolean{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${matricula}</strMatricula>
                  <strContrasenia>${password}</strContrasenia>
                  <tipoUsuario>ALUMNO</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        usuarioApiService.getCokies()
        return try {
            var respuestDatos=usuarioApiService.getAcceso(requestBody).string().split("{","}")
            if(respuestDatos.size>1){
                val result = Gson().fromJson("{"+respuestDatos[1]+"}", UsuarioAccess::class.java)
                result.acceso.equals("true")
            } else
                false
        }catch (e: IOException){
            false
        }
    }

    override suspend fun getInfo():String{
        var alumnoInfo= UsuarioInfo("")
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        return try {
            val respuestaInfo=usuarioInfo.getInfo(requestBody).string().split("{","}")
            if(respuestaInfo.size>1){
                ""+respuestaInfo[1]
            } else
                ""
        }catch (e:IOException){
            ""
        }
    }
}

