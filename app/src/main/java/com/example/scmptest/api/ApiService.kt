package com.example.scmptest.api

import com.example.scmptest.api.model.LoginRequest
import com.example.scmptest.api.model.LoginResponse
import com.example.scmptest.api.model.StaffsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(
        @Query("delay") delaySeconds: Int,
        @Body request: LoginRequest): LoginResponse

    @GET("users")
    suspend fun getStaff(
        @Query("page") page: Int,
        @Header("Authorization") token: String
    ): StaffsResponse
}