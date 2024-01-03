package com.example.signin2

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Apii {
    @POST("/api/v1/users/signin")
    suspend fun signin(@Body Logindata : LoginCheck) : Response<LoginResponse>

    @POST("/api/v1/users/register")
    suspend fun register(@Body Registerdata : RegisterCheck) : Response<RegisterResponse>

    @GET("/api/v1/users/verify/{token}")
    suspend fun token(@Path ("token") token : String) : Response<TokenResponse>

    @GET("/api/v1/users/profile")
    suspend fun getUserProfile(
        @Header("Authorization") authorization : String
    ) : Response<ProfileResponse>

    @PUT("/api/v1/users/profile")
    suspend fun updateUserProfile(@Body updatedata : UpdateUserRequest) : Response<ProfileResponse>
}