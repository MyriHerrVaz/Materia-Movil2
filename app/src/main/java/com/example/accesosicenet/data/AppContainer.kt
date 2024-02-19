package com.example.accesosicenet.data

import ApiService
import com.example.accesosicenet.network.InfoApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

interface AppContainer{
    val usuariosRepository:usuarioRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL =
        "https://sicenet.surguanajuato.tecnm.mx/"

    private val interceptor= CookiesInterceptor()

    private val cliente = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL).client(cliente)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofitServiceInfo : InfoApiService by lazy {
        retrofit.create(InfoApiService::class.java)
    }

    override val usuariosRepository: usuarioRepository by lazy {
        UsuariosRepository(retrofitService,retrofitServiceInfo)
    }
}

class CookiesInterceptor : Interceptor {

    // Variable que almacena las cookies
    private var cookies: List<String> = emptyList()

    // Método para establecer las cookies
    fun setCookies(cookies: List<String>) {
        this.cookies = cookies
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Agregar las cookies al encabezado de la solicitud
        if (cookies.isNotEmpty()) {
            val cookiesHeader = StringBuilder()
            for (cookie in cookies) {
                if (cookiesHeader.isNotEmpty()) {
                    cookiesHeader.append("; ")
                }
                cookiesHeader.append(cookie)
            }

            request = request.newBuilder()
                .header("Cookie", cookiesHeader.toString())
                .build()
        }

        val response = chain.proceed(request)

        // Almacenar las cookies de la respuesta para futuras solicitudes
        val receivedCookies = response.headers("Set-Cookie")
        if (receivedCookies.isNotEmpty()) {
            setCookies(receivedCookies)
        }

        return response
    }
}