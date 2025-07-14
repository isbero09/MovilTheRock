package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Menbresia
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MenbresiaApiService {
    @GET("api/menbresia")
    suspend fun getMenbresia(): List<Menbresia>

    @GET("api/menbresia/{id}")
    suspend fun getMenbresia(@Path("id") id: Int): Menbresia

    @POST("api/menbresia")
    suspend fun postMenbresia(@Body menbresia: Menbresia): Menbresia

    @PUT("api/menbresia/{id}")
    suspend fun putMenbresia(@Body menbresia: Menbresia, @Path("id") id: Int): Menbresia

    @DELETE("api/menbresia/{id}")
    suspend fun deleteMenbresia(@Path("id") id: Int): Menbresia

    companion object {
        @Volatile
        private var INSTANCE: MenbresiaApiService? = null

        private const val BASE_URL = "http://10.0.2.2:8000"

        fun getApiManager(): MenbresiaApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MenbresiaApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
    }
}