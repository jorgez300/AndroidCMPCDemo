package com.demo.demobaseandroid2.data.repository

//package com.demo.demobaseandroid2.data.repository

import com.demo.demobaseandroid2.data.dao.AppConfigDao
import com.demo.demobaseandroid2.data.entity.AppConfig
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para gestionar las configuraciones de la aplicación.
 * Proporciona métodos para insertar, actualizar, eliminar y consultar configuraciones.
 */
class AppConfigRepository(private val appConfigDao: AppConfigDao) {

    /**
     * Obtiene todas las configuraciones como un Flow.
     * @return Un Flow que emite una lista de configuraciones.
     */
    fun getAllConfigs(): Flow<List<AppConfig>> {
        return appConfigDao.getAllConfigs()
    }

    /**
     * Obtiene una configuración por su clave.
     * @param key La clave de la configuración.
     * @return La configuración correspondiente, o null si no se encuentra.
     */
    suspend fun getConfig(key: String): AppConfig? {
        return appConfigDao.getConfig(key)
    }

    /**
     * Inserta una nueva configuración en la base de datos.
     * @param config La configuración a insertar.
     */
    suspend fun insert(config: AppConfig) {
        appConfigDao.insert(config)
    }

    /**
     * Actualiza una configuración existente en la base de datos.
     * @param config La configuración a actualizar.
     */
    suspend fun update(config: AppConfig) {
        appConfigDao.update(config)
    }

    /**
     * Elimina una configuración de la base de datos.
     * @param key La clave de la configuración a eliminar.
     */
    suspend fun delete(key: String) {
        appConfigDao.delete(key)
    }

    /**
     * Inserta o actualiza una configuración en la base de datos.
     * Si la configuración ya existe, se actualiza; de lo contrario, se inserta.
     * @param config La configuración a insertar o actualizar.
     */
    suspend fun upsert(config: AppConfig) {
        val existingConfig = appConfigDao.getConfig(config.key)
        if (existingConfig != null) {
            appConfigDao.update(config)
        } else {
            appConfigDao.insert(config)
        }
    }
}