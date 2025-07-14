package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Ingreso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IngresoApiService {
    @GET("api/ingresos")
    suspend fun getIngresos(): List<Ingreso>

    companion object {
        @Volatile
        private var INSTANCE: IngresoApiService? = null

        private const val BASE_URL = "http://10.0.2.2:8000"

        fun getApiManager(): IngresoApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IngresoApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}