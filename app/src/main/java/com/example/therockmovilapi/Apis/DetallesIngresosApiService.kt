package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.DetallesIngresos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DetallesIngresosApiService {
    @GET ("api/detallesIngresos")
    suspend fun  getDetallesIngresos() : List<DetallesIngresos>

    @GET("api/detallesIngresos/{id}")
    suspend fun getDetallesIngresos(@Path("id") id: Int): DetallesIngresos

    @POST("api/detallesIngresos")
    suspend fun postDetallesIngresos(@Body detallesIngresos: DetallesIngresos): DetallesIngresos

    @PUT("api/detallesIngresos/{id}")
    suspend fun putDetallesIngresos(@Body detallesIngresos: DetallesIngresos, @Path("id") id:
    Int): DetallesIngresos

    @DELETE("api/detallesIngresos/{id}")
    suspend fun deleteDetallesIngresos(@Path("id") id: Int): DetallesIngresos

    companion object {
        @Volatile
        private var INSTANCE: DetallesIngresosApiService? = null
        private var BASE_URL = "http://10.0.2.2:8000"
        fun getApiManager(): DetallesIngresosApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DetallesIngresosApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}