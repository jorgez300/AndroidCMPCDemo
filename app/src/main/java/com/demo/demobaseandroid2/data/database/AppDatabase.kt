package com.demo.demobaseandroid2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.demobaseandroid2.data.dao.UserDao
import com.demo.demobaseandroid2.data.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}