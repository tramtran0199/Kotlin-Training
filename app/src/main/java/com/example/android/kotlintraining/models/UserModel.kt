package com.example.android.kotlintraining.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String
) : Parcelable {}