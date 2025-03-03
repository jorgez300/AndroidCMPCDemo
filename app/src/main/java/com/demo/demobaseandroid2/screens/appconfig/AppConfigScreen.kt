package com.demo.demobaseandroid2.screens.appconfig

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.demo.demobaseandroid2.data.entity.AppConfig
import com.demo.demobaseandroid2.data.repository.AppConfigRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppConfigScreen(repository: AppConfigRepository) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var editingConfig by remember { mutableStateOf<AppConfig?>(null) }
    var showAddConfigDialog by remember { mutableStateOf(false) }
    val configs by repository.getAllConfigs().collectAsState(initial = emptyList())

    // Insertar configuraciones iniciales si la base de datos está vacía
    LaunchedEffect(Unit) {
        if (configs.isEmpty()) {
            val initialConfigs = listOf(
                AppConfig("theme_mode", "light"),
                AppConfig("language", "en"),
                AppConfig("notifications", "enabled")
            )
            initialConfigs.forEach { config ->
                // Verificar si la configuración ya existe antes de insertarla
                val existingConfig = repository.getConfig(config.key)
                if (existingConfig == null) {
                    repository.insert(config)
                }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "App Configurations", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar una nueva configuración
        Button(
            onClick = { showAddConfigDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Configuration")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de configuraciones
        if (configs.isNotEmpty()) {
            LazyColumn {
                items(configs) { config ->
                    ConfigItem(
                        config = config,
                        onEdit = { editingConfig = config }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        } else {
            Text("No configurations found.", style = MaterialTheme.typography.bodyMedium)
        }
    }

    // Diálogo para agregar una nueva configuración
    if (showAddConfigDialog) {
        AddConfigDialog(
            onDismiss = { showAddConfigDialog = false },
            onSave = { key, value ->
                scope.launch {
                    // Verificar si la configuración ya existe antes de insertarla
                    val existingConfig = repository.getConfig(key)
                    if (existingConfig == null) {
                        repository.insert(AppConfig(key, value))
                        Toast.makeText(context, "Configuration added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Configuration with key '$key' already exists",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    showAddConfigDialog = false
                }
            }
        )
    }

    // Diálogo para editar configuraciones
    if (editingConfig != null) {
        EditConfigDialog(
            config = editingConfig!!,
            onDismiss = { editingConfig = null },
            onSave = { updatedConfig ->
                scope.launch {
                    repository.update(updatedConfig)
                    Toast.makeText(context, "Configuration updated", Toast.LENGTH_SHORT).show()
                    editingConfig = null
                }
            }
        )
    }
}

@Composable
fun ConfigItem(config: AppConfig, onEdit: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Key: ${config.key}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Value: ${config.value}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onEdit, modifier = Modifier.fillMaxWidth()) {
                Text("Edit")
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun AddConfigDialog(onDismiss: () -> Unit, onSave: (String, String) -> Unit) {
    var key by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Add Configuration", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = key,
                    onValueChange = { key = it },
                    label = { Text("Key") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Value") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (key.isNotEmpty() && value.isNotEmpty()) {
                            onSave(key, value)
                        } else {
                            // Mostrar un mensaje de error si los campos están vacíos
                        }
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
fun EditConfigDialog(config: AppConfig, onDismiss: () -> Unit, onSave: (AppConfig) -> Unit) {
    var key by remember { mutableStateOf(config.key) }
    var value by remember { mutableStateOf(config.value) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Edit Configuration", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = key,
                    onValueChange = { key = it },
                    label = { Text("Key") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false // No permitir editar la clave
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Value") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        onSave(config.copy(value = value))
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}