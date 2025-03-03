package com.demo.demobaseandroid2.data.entity
//package com.demo.demobaseandroid2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_config")
data class AppConfig(
    @PrimaryKey val key: String, // Clave única para la configuración (ej: "theme_mode")
    val value: String // Valor de la configuración (ej: "dark")
)