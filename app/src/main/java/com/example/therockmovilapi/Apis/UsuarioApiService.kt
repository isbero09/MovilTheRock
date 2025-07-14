package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Usuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsuarioApiService {
    @GET("api/usuarios")
    suspend fun getUsuarios(): List<Usuario>

    companion object {
        @Volatile
        private var INSTANCE: UsuarioApiService? = null

        private const val BASE_URL = "http://10.0.2.2:8000"

        fun getApiManager(): UsuarioApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UsuarioApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}