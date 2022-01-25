package com.example.android.kotlintraining.apis

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UserDetailApiService {
    @GET("users/{selectedUser}")
    suspend fun getUserDetail(@Path("selectedUser") selectedUser: String): UserDetailProperty
}

object UserDetailApi {
    val retrofitService : UserDetailApiService by lazy { retrofit.create(UserDetailApiService::class.java) }
}
