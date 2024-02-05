package com.example.marsphotos.network


//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}