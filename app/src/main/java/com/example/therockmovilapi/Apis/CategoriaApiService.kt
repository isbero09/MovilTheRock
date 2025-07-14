package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Categoria
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoriaApiService {
    @GET("api/categorias")
    suspend fun getCategoria(): List<Categoria>

    @GET("api/categorias/{id}")
    suspend fun getCategoria(@Path("id") id: Int): Categoria

    @POST("api/categorias")
    suspend fun postCategoria(@Body categoria: Categoria): Categoria

    @PUT("api/categorias/{id}")
    suspend fun putCategoria(@Body categoria: Categoria, @Path("id") id: Int): Categoria

    @DELETE("api/categorias/{id}")
    suspend fun deleteCategoria(@Path("id") id: Int): Categoria


    companion object {
        @Volatile
        private var INSTANCE: CategoriaApiService? = null

        private const val BASE_URL = "http://10.0.2.2:8000"

        fun getApiManager(): CategoriaApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CategoriaApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}