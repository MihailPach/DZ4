package com.example.dz4.database

import androidx.room.*
import com.example.dz4.model.User

@Dao
interface UserDao {

    @Query("SELECT* FROM User")
  fun getCities(): List<User>
    @Insert
    fun insertAll(vararg users: User)
    @Delete
    fun delete(user: User)
}