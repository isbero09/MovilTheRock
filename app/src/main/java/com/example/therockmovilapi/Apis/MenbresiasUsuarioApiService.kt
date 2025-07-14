package com.example.therockmovilapi.Apis

import com.example.therockmovilapi.Entities.Menbresia
import com.example.therockmovilapi.Entities.MenbresiasUsuario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MenbresiasUsuarioApiService {
    @GET("api/menbresiasUsuario")
    suspend fun getMenbresiasUsuario(): List<MenbresiasUsuario>
    @GET("api/menbresiasUsuario/{id}")
    suspend fun getMenbresiasUsuario(@Path("id") id: Int): MenbresiasUsuario
    @POST("api/menbresiasUsuario")
    suspend fun postMenbresiasUsuario(@Body menbresiasUsuario: MenbresiasUsuario): MenbresiasUsuario
    @PUT("api/menbresiasUsuario/{id}")
    suspend fun putMenbresiasUsuario(@Body menbresia: Menbresia, @Path("id")
    id: Int): MenbresiasUsuario
    @DELETE("api/menbresiasUsuario/{id}")
    suspend fun deleteMenbresiasUsuario(@Path("id") id: Int): MenbresiasUsuario
}