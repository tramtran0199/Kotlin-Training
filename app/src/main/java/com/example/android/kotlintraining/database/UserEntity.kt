package com.example.android.kotlintraining.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.kotlintraining.models.UserModel

@Entity
data class UserEntity constructor(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String
)

fun List<UserEntity>.asDomainModel(): List<UserModel> {
    return map { UserModel(
        id = it.id,
        login = it.login,
        avatarUrl = it.avatarUrl,
        url = it.url
    )}
}