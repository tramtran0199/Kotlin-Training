package com.example.android.kotlintraining.apis

import com.squareup.moshi.Json

data class UserDetailProperty (
    val id: Int,
    val name: String?,
    @Json(name = "avatar_url") val avatarUrl: String,
    val followers: Int,
    @Json(name = "created_at") val createdAt: String,
    val blog: String
)