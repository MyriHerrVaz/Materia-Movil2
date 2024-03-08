import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService{

    // Acceso al sicenet
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/accesoLogin\"",
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getAcceso(
        @Body requestBody: RequestBody
    ): ResponseBody

    // Imformacion del alumno
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAlumnoAcademicoWithLineamiento\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getInfo(
        @Body requestBody: RequestBody
    ): ResponseBody

    //Carga academica del alumno

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getCargaAcademicaByAlumno\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getCargaAcademica(
        @Body requestBody: RequestBody
    ): ResponseBody


    //Cardex de alumno
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllKardexConPromedioByAlumno\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getCardex(
        @Body requestBody: RequestBody
    ): ResponseBody


    // Calificaciones por unidad
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getCalifUnidadesByAlumno\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getCalificacionUnidad(
        @Body requestBody: RequestBody
    ): ResponseBody

    //Calificaciones finales
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAllCalifFinalByAlumnos\""
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getCalificacionFinal(
        @Body requestBody: RequestBody
    ): ResponseBody

    //cookies
    @GET("ws/wsalumnos.asmx")
    suspend fun getCookies(): ResponseBody

}
