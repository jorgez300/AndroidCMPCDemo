package com.demo.demobaseandroid2.screens.convectorpt.segmented

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.foundation.clickable

@Composable
fun DestinoScreen() {
    val backgroundColor = Color(0xFFCDCACD)
    val textColor = Color(0xFF000000)
    val cardBackgroundColor = Color(0xFFE8E5E7)
    val headerTextColor = Color(0xFF2E3D50)
    val checkboxColor = Color(0xFFE56C29)
    val buttonColor = Color(0xFFBFBCBF)
    val buttonTextColor = Color(0xFF1F1D1F)

    var showDialog by remember { mutableStateOf(false) }
    var showModal by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var firma by remember { mutableStateOf("") }

    val checklistItems = listOf(
        "Validar llegada Destino.",
        "Foto Respaldo.",
        "Recepcionado Por.",
        "Tarea Descarpe.",
        "Termino de Viaje."
    )

    val responses = remember { mutableStateMapOf<String, Boolean?>() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Título
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(16.dp)
            ) {
                Text(
                    text = "DESTINO",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp, color = headerTextColor
                    )
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(cardBackgroundColor, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text("OT N°: 243949", color = textColor)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("TRANSPORTE: 0", color = textColor)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Destino: Puerto Montt pt", color = textColor)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Comuna: Puerto Montt", color = textColor)
                        }
                    }
                }

                item {
                    checklistItems.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(cardBackgroundColor, shape = RoundedCornerShape(12.dp))
                                .padding(16.dp)
                        ) {
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            if (item != "Tarea Descarpe") {
                                                selectedItem = item
                                                showModal = true
                                            }
                                        }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = checkboxColor,
                                        modifier = Modifier.size(54.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "${index + 1}. $item",
                                        color = textColor
                                    )
                                }
                                if (index == 3) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Sí", modifier = Modifier.padding(end = 8.dp))
                                        Checkbox(
                                            checked = responses[item] == true,
                                            onCheckedChange = { responses[item] = true },
                                            colors = CheckboxDefaults.colors(checkedColor = checkboxColor),
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text("No", modifier = Modifier.padding(end = 8.dp))
                                        Checkbox(
                                            checked = responses[item] == false,
                                            onCheckedChange = { responses[item] = false },
                                            colors = CheckboxDefaults.colors(checkedColor = checkboxColor),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { showDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor,
                                contentColor = buttonTextColor
                            )
                        ) {
                            Text("ENVIAR", color = buttonTextColor)
                        }
                        Button(
                            onClick = { responses.clear() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor,
                                contentColor = buttonTextColor
                            )
                        ) {
                            Text("LIMPIAR", color = buttonTextColor)
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Información") },
            text = { Text("Se transfirió checklist") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Aceptar")
                }
            }
        )
    }

    if (showModal) {
        AlertDialog(
            containerColor = Color(0xFFE8E5E7),
            modifier = Modifier
                .background(Color(0xFFCDCACD)) // Color de fondo del diálogo
                .padding(16.dp),
            onDismissRequest = { showModal = false },
            title = { Text(selectedItem) },
            text = {
                Column {
                    Text("Nombre", color = buttonTextColor)
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = rut,
                        onValueChange = { rut = it },
                        label = { Text("RUT") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = firma,
                        onValueChange = { firma = it },
                        label = { Text("Firma") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                IconButton(
                    onClick = { showModal = false }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Confirmar", tint = Color.Green)
                }
            },
            dismissButton = {
                IconButton(
                    onClick = { showModal = false }
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.Red)
                }
            }
        )
    }
}
