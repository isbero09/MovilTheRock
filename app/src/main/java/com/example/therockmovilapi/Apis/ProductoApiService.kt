package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Producto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductoApiService {
    @GET("api/producto")
    suspend fun getProducto(): List<Producto>


    companion object {
        @Volatile
        private var INSTANCE: ProductoApiService? = null

        private const val BASE_URL = "http://10.0.2.2:8000"

        fun getApiManager(): ProductoApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ProductoApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}