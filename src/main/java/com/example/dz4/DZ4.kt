package com.example.dz4

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.dz4.database.AppDatabase


class DZ4:Application() {
    private var _database: AppDatabase? = null
    val database get() = requireNotNull(_database)

    override fun onCreate() {
        super.onCreate()
        _database = Room
        .databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        )
            .allowMainThreadQueries()
            .build()
    }
}
val Context.appDatabase:AppDatabase
get() = when(this) {
    is DZ4-> database
    else -> applicationContext.appDatabase
}