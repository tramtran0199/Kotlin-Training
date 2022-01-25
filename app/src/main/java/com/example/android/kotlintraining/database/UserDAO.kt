package com.example.android.kotlintraining.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM userentity")
    fun getListUser(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)
}

@Database(entities = [UserEntity::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {
    abstract val userDao: UserDAO
}

private lateinit var INSTANCE: UsersDatabase

fun getDatabase(context: Context): UsersDatabase {
    synchronized(UsersDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                UsersDatabase::class.java,
                "users").build()
        }
    }
    return INSTANCE
}