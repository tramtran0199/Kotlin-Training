package com.example.android.kotlintraining.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.kotlintraining.database.UsersDatabase
import com.example.android.kotlintraining.database.asDomainModel
import com.example.android.kotlintraining.apis.ListUserApi
import com.example.android.kotlintraining.apis.asDatabaseModel
import com.example.android.kotlintraining.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val database: UsersDatabase) {
    val users: LiveData<List<UserModel>> = Transformations.map(database.userDao.getListUser()) {
        it.asDomainModel()
    }
    suspend fun refreshListUser() {
        withContext(Dispatchers.IO) {
            val playlist = ListUserApi.retrofitService.getProperties()
            database.userDao.insertAll(playlist.asDatabaseModel())
        }
    }
}