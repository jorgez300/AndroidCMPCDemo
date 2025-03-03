package com.demo.demobaseandroid2.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.demo.demobaseandroid2.data.dao.UserDao
import com.demo.demobaseandroid2.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .setJournalMode(RoomDatabase.JournalMode.TRUNCATE) // Desactiva WAL
                    .build()
                INSTANCE = instance
                instance
            }
        }


    }
}