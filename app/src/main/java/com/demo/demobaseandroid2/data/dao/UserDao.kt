package com.demo.demobaseandroid2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.demobaseandroid2.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
}