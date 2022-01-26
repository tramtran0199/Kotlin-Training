package com.example.android.kotlintraining.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.kotlintraining.apis.UserDetailApi
import com.example.android.kotlintraining.apis.asDatabaseModel
import com.example.android.kotlintraining.database.AppDatabase
import com.example.android.kotlintraining.database.asDomainModel
import com.example.android.kotlintraining.models.UserDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDetailRepository(private val database: AppDatabase, private  val login: String) {
    val userDetail: LiveData<UserDetailModel> = Transformations.map(database.userDetailDAO.getUser(login)) {
        it?.asDomainModel()
    }

    suspend fun refreshUser() {
        withContext(Dispatchers.IO) {
            val user = UserDetailApi.retrofitService.getUserDetail(login)
            database.userDetailDAO.insert(user.asDatabaseModel())
        }
    }
}