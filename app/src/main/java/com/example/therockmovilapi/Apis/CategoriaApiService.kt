package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Categoria
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CategoriaApiService {
    @GET("api/categorias")
    suspend fun getCategorias(): List<Categoria>

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