package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Producto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductoApiService {
    @GET("api/producto")
    suspend fun getProducto(): List<Producto>

    @GET("api/productos/{id}")
    suspend fun getProducto(@Path("id") id: Int): Producto

    @POST("api/productos")
    suspend fun postProducto(@Body producto: Producto): Producto

    @PUT("api/productos/{id}")
    suspend fun putProducto(@Body producto: Producto, @Path("id") id: Int): Producto

    @DELETE("api/productos/{id}")
    suspend fun deleteProducto(@Path("id") id: Int): Producto


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