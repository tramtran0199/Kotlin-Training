package com.example.android.kotlintraining.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDetailDAO {
    @Query("SELECT * FROM userdetailentity WHERE login = :key")
    fun getUser(key: String): LiveData<UserDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(detail: UserDetailEntity)
}
