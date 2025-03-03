package com.demo.demobaseandroid2.data.database

import android.content.Context
import com.demo.demobaseandroid2.data.database.AppDatabase

object DatabaseProvider {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            val instance = AppDatabase.getDatabase(context)
            database = instance
            instance
        }
    }
}