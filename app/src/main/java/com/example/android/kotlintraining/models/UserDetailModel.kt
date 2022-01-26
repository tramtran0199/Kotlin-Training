package com.example.android.kotlintraining.models

data class UserDetailModel(
    val id: Int,
    val login: String,
    val name: String?,
    val avatarUrl: String,
    val followers: Int,
    val createdAt: String,
    val blog: String
)