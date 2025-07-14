package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Usuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioApiService {
    @GET("api/usuarios")
    suspend fun getUsuario(): List<Usuario>

    @GET("api/usuarios/{id}")
    suspend fun getUsuario(@Path("id") id: Int): Usuario

    @POST("api/usuarios")
    suspend fun postUsuario(@Body usuario: Usuario): Usuario

    @PUT("api/usuarios/{id}")
    suspend fun putUsuario(@Body usuario: Usuario, @Path("id") id: Int): Usuario

    @DELETE("api/usuarios/{id}")
    suspend fun deleteUsuario(@Path("id") id: Int): Usuario

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