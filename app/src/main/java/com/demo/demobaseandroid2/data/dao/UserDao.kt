package com.demo.demobaseandroid2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.demobaseandroid2.data.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun getUserByUsernameAndPassword(username: String, password: String): User?
}