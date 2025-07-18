package com.example.nasapictureofdaymvvm.data

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): ApodResponse
}