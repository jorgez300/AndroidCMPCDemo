package com.demo.demobaseandroid2.screens.convectorpt.segmented

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChecklistScreen() {
    val backgroundColor = Color(0xFFF5F5F5)
    val textColor = Color(0xFF000000)
    val cardBackgroundColor = Color(0xFFE8E5E7)
    val tableHeaderColor = Color(0xFFC3C4C9)
    val headerTextColor = Color(0xFF2E3D50)
    val checkboxColor = Color(0xFF529740)
    val buttonColor = Color(0xFFBFBCBF)
    val buttonTextColor = Color(0xFF1F1D1F)
    var showDialog by remember { mutableStateOf(false) }
    val checklistItems = listOf(
        "CONFORME CON CARGUÍO",
        "CONFORME CON AMARRE",
        "CONFORME CON ENCARPE",
        "CONFORME CUIDADO DE ELEMENTOS ESTIVA"
    )
    val responses = remember { mutableStateMapOf<String, Boolean?>() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Título con su propio margen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(16.dp)
            ) {
                Text(
                    text = "CHECK LIST DE CARGA EN ORIGEN",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold, fontSize = 18.sp, color = headerTextColor
                    )
                )
            }

            // LazyColumn para tener el scroll y los elementos desplazables
            LazyColumn(
                modifier = Modifier
                    .weight(1f)  // Esto asegura que el LazyColumn ocupe el espacio disponible entre el título y el footer
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    // Sección de carga de imágenes
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(cardBackgroundColor, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Espacio reservado para carga de imágenes",
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                item {
                    // Encabezado de la tabla con su propio margen y fondo
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(tableHeaderColor)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "DECLARACIÓN DE CONFORMIDAD DEL CONDUCTOR",
                                modifier = Modifier.weight(0.7f),
                                fontWeight = FontWeight.Bold,
                                color = headerTextColor
                            )
                            Text(
                                "SI",
                                modifier = Modifier.weight(0.15f),
                                fontWeight = FontWeight.Bold,
                                color = headerTextColor
                            )
                            Text(
                                "NO",
                                modifier = Modifier.weight(0.15f),
                                fontWeight = FontWeight.Bold,
                                color = headerTextColor
                            )
                        }

                        // Elementos de la lista
                        checklistItems.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(item, modifier = Modifier.weight(0.7f), color = textColor)
                                Checkbox(
                                    checked = responses[item] == true,
                                    onCheckedChange = { responses[item] = true },
                                    modifier = Modifier.weight(0.15f),
                                    colors = CheckboxDefaults.colors(checkedColor = checkboxColor)
                                )
                                Checkbox(
                                    checked = responses[item] == false,
                                    onCheckedChange = { responses[item] = false },
                                    modifier = Modifier.weight(0.15f),
                                    colors = CheckboxDefaults.colors(checkedColor = checkboxColor)
                                )
                            }
                        }
                    }
                }
            }

            // Espacio reservado para footer (botones)
            Spacer(modifier = Modifier.height(16.dp))

            // Botones Enviar y Limpiar con altura mayor (rectangulares)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.height(48.dp), // Botones más rectangulares
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor, // Fondo de los botones
                        contentColor = buttonTextColor // Texto blanco
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("ENVIAR", color = buttonTextColor)
                }
                Button(
                    onClick = { responses.clear() },
                    modifier = Modifier.height(48.dp), // Botones más rectangulares
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor, // Fondo de los botones
                        contentColor = buttonTextColor // Texto blanco
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("LIMPIAR", color = buttonTextColor)
                }


            }


            Box(
                modifier = Modifier
                    .fillMaxWidth() // Asegura que el Box ocupe todo el ancho
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "SIM-F9160v1.0.0",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = textColor
                    ),
                    modifier = Modifier.align(Alignment.Center) // Centra el texto dentro del Box
                )
            }
            Spacer(modifier = Modifier.height(36.dp))

        }
    }

    // Mostrar alerta
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
}
