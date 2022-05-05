package com.example.dz4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dz4.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao():UserDao
}