package com.example.android.kotlintraining.apis

import com.example.android.kotlintraining.database.UserEntity
import com.squareup.moshi.Json

data class ListUserProperty (
    val id: Int,
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    val url: String
)

fun List<ListUserProperty>.asDatabaseModel(): List<UserEntity> {
    return map {
        UserEntity(
            id = it.id,
            login = it.login,
            avatarUrl = it.avatarUrl,
            url = it.url
        )
    }
}
