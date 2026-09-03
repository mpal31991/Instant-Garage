package com.example.instantgarage.data.remote

import com.example.instantgarage.data.model.Mechanic
import com.example.instantgarage.data.model.MechanicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MechanicApi {

    @GET("b/6a97dfa4f5f4af5e29600958?meta=false")
    suspend fun getMechanics(): Response<MechanicResponse>

    @GET("b/6a97dfa4f5f4af5e29600958?meta=false")
    suspend fun getMechanicById(
        @Header("X-JSON-Path") headerValue: String
    ): Response<List<Mechanic>>
}