package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Venta
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface VentaApiService {
    @GET("api/venta")
    suspend fun getVentas(): List<Venta>

    companion object {
        @Volatile
        private var INSTANCE: VentaApiService? = null

        private const val BASE_URL = "http://10.0.2.2:8000"

        fun getApiManager(): VentaApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(VentaApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}