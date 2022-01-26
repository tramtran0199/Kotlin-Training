package com.example.android.kotlintraining.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.kotlintraining.models.UserDetailModel
import com.example.android.kotlintraining.models.UserModel
import com.squareup.moshi.Json

@Entity
data class UserDetailEntity(
    @PrimaryKey
    val id: Int,
    val login: String,
    val name: String?,
    val avatarUrl: String,
    val followers: Int,
    val createdAt: String,
    val blog: String
)

fun UserDetailEntity.asDomainModel(): UserDetailModel {
    return UserDetailModel(
        id = id,
        login = login,
        name = name,
        avatarUrl = avatarUrl,
        followers = followers,
        createdAt = createdAt,
        blog = blog
    )
}