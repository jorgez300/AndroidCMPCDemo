package com.demo.demobaseandroid2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.demo.demobaseandroid2.data.entity.AppConfig
import kotlinx.coroutines.flow.Flow

@Dao
interface AppConfigDao {
    @Insert
    suspend fun insert(config: AppConfig)

    @Update
    suspend fun update(config: AppConfig)

    @Query("DELETE FROM app_config WHERE `key` = :key") // Escapar `key`
    suspend fun delete(key: String)

    @Query("SELECT * FROM app_config WHERE `key` = :key") // Escapar `key`
    suspend fun getConfig(key: String): AppConfig?

    @Query("SELECT * FROM app_config")
    fun getAllConfigs(): Flow<List<AppConfig>>
}