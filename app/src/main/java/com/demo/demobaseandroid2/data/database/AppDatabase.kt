package com.demo.demobaseandroid2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.demobaseandroid2.data.dao.UserDao
import com.demo.demobaseandroid2.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}