package com.example.android.kotlintraining.apis

import com.example.android.kotlintraining.database.UserDetailEntity
import com.squareup.moshi.Json

data class UserDetailProperty (
    val id: Int,
    val login: String,
    val name: String?,
    @Json(name = "avatar_url") val avatarUrl: String,
    val followers: Int,
    @Json(name = "created_at") val createdAt: String,
    val blog: String
)

fun UserDetailProperty.asDatabaseModel(): UserDetailEntity {
    return UserDetailEntity(
        id = id,
        login = login,
        name = name,
        avatarUrl = avatarUrl,
        followers = followers,
        createdAt = createdAt,
        blog = blog
    )
}