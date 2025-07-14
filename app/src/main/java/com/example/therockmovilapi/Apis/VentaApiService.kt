package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Venta
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VentaApiService {
    @GET("api/venta")
    suspend fun getVentas(): List<Venta>

    @GET("api/ventas/{id}")
    suspend fun getVenta(@Path("id") id: Int): Venta

    @POST("api/ventas")
    suspend fun postVenta(@Body venta: Venta): Venta

    @PUT("api/ventas/{id}")
    suspend fun putVenta(@Body venta: Venta, @Path("id") id: Int): Venta

    @DELETE("api/ventas/{id}")
    suspend fun deleteVenta(@Path("id") id: Int): Venta

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