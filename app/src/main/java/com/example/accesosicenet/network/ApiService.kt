import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://sicenet.surguanajuato.tecnm.mx"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_URL).build()

interface ApiService{
    @GET("Usuario")
    suspend fun getUsuario():String
}

object Api{
    val retrofitService: ApiService by Lazy { retrofit.create(ApiService::class.java) }
}