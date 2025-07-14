package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Ingreso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface IngresoApiService {
    @GET("api/ingresos")
    suspend fun getIngreso(): List<Ingreso>

    @GET("api/ingresos/{id}")
    suspend fun getIngreso(@Path("id") id: Int): Ingreso

    @POST("api/ingresos")
    suspend fun postIngreso(@Body ingreso: Ingreso): Ingreso

    @PUT("api/ingresos/{id}")
    suspend fun putIngreso(@Body ingreso: Ingreso, @Path("id") id: Int): Ingreso

    @DELETE("api/ingresos/{id}")
    suspend fun deleteIngreso(@Path("id") id: Int): Ingreso

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