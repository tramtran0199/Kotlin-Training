package com.example.android.kotlintraining.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, UserDetailEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val userDAO: UserDAO
    abstract val userDetailDAO: UserDetailDAO
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "users").build()
        }
    }
    return INSTANCE
}