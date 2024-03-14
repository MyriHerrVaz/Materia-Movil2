package com.example.accesosicenet.data

import ApiService
import android.util.Log
import okhttp3.RequestBody.Companion.toRequestBody
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

interface usuarioRepository{
    suspend fun getAccess(matricula: String, password: String): String
    suspend fun getInfo(matricula: String):String
    suspend fun getCargaAcademica(): String
    suspend fun getCardex(lineamiento: String): String
    suspend fun getCalificacionUnidad(): String
    suspend fun getCalificacionFinal(modoEducativo:String): String
}
class UsuariosRepository (
    private val usuarioApiService: ApiService
):usuarioRepository{
    override suspend fun getAccess(matricula: String, password: String ):String{
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
        usuarioApiService.getCookies()
        var result=""
        try {
            var respuestDatos=usuarioApiService.getAcceso(requestBody).string().split("{","}")
            if(respuestDatos.size>1){
                 //result = Gson().fromJson("{"+respuestDatos[1]+"}", UsuarioAccess::class.java)
                result = "{"+respuestDatos[1]+"}"
                Log.d("Infoacceso",""+result)
            } else
                false
        }catch (e: IOException){
            false
        }
        return result
    }

    override suspend fun getInfo(matricula: String):String{
        var alumnoInfo=""
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            val respuestaInfo=usuarioApiService.getInfo(requestBody).string().split("{","}")
            if(respuestaInfo.size>1){
                //alumnoInfo = Gson().fromJson("{"+respuestaInfo[1]+"}", UsuarioInfo::class.java)
                alumnoInfo ="{"+respuestaInfo[1]+"}"
                return alumnoInfo
            } else
                return alumnoInfo
        }catch (e:IOException){
            return alumnoInfo
        }
    }
    //Horarios del alumno
    override suspend fun getCargaAcademica():String{
        var Horario=""
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getCargaAcademicaByAlumno xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            val respuestaHorario = usuarioApiService.getCargaAcademica(requestBody).string()
            Log.d("ObtenerHorario",""+respuestaHorario)
            Horario = extracJson(respuestaHorario,"getCargaAcademicaByAlumnoResult")
            return Horario
        }catch (e:IOException){
            return Horario
        }
    }
    //Cardex del alumno
    override suspend fun getCardex(lineamiento:String):String{
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAllKardexConPromedioByAlumno xmlns="http://tempuri.org/">
                  <aluLineamiento>${lineamiento}</aluLineamiento>
                </getAllKardexConPromedioByAlumno>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            Log.d("ObtenerCardex",""+extracJson(usuarioApiService.getCardex(requestBody).string(),"getAllKardexConPromedioByAlumnoResult"))
            return extracJson(usuarioApiService.getCardex(requestBody).string(),"getAllKardexConPromedioByAlumnoResult")

        }catch (e:Exception){
            return ""
        }
    }
    //Calificacion por unidad
    override suspend fun getCalificacionUnidad(): String{
        var unidades = ""
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                <soap:Body>
                    <getCargaAcademicaByAlumno xmlns="http://tempuri.org/" />
                </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            val respuestaUnidad=usuarioApiService.getCalificacionUnidad(requestBody).string()
            unidades =  extracJson(respuestaUnidad,"getCalifUnidadesByAlumnoResult")
            Log.d("Obtener Unidades",""+unidades)
                    //Gson().fromJson(unidades, object : TypeToken<List<CalifUnidad>>(){}.type)
            return unidades
        }catch (e:IOException){
            return unidades
        }
    }
    //Calificacion Final
    override suspend fun getCalificacionFinal(modoEducativo:String):String{
        var final = ""
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAllCalifFinalByAlumnos xmlns="http://tempuri.org/">
                  <bytModEducativo>${modoEducativo}</bytModEducativo>
                </getAllCalifFinalByAlumnos>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        val requestBody=xml.toRequestBody()
        try {
            val respuestaFinal=usuarioApiService.getCalificacionFinal(requestBody).string()
            final = extracJson(respuestaFinal,"getAllCalifFinalByAlumnosResult")
            return final
        }catch (e:IOException){
            return final
        }
    }

    fun extracJson(responseBody: String, tag: String):String{
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(responseBody.reader())

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == tag) {
                parser.next()
                return parser.text
            }
            eventType = parser.next()
        }
        return ""
    }
}

