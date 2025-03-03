package com.demo.demobaseandroid2.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.demo.demobaseandroid2.data.dao.AppConfigDao
import com.demo.demobaseandroid2.data.dao.UserDao
import com.demo.demobaseandroid2.data.entity.AppConfig
import com.demo.demobaseandroid2.data.model.User

@Database(
    entities = [User::class, AppConfig::class], // Agrega AppConfig a la lista de entidades
    version = 2, // Incrementa la versión de la base de datos
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun appConfigDao(): AppConfigDao // Agrega el DAO de configuración

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
                    .fallbackToDestructiveMigration() // Elimina y recrea la base de datos si hay un cambio de esquema
                    .setJournalMode(RoomDatabase.JournalMode.TRUNCATE) // Desactiva WAL
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}