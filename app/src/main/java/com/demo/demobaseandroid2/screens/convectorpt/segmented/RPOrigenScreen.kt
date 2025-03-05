package com.demo.demobaseandroid2.screens.convectorpt.segmented

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun RPOrigenScreen() {
    val backgroundColor = Color(0xFFE8E5E7) // Fondo claro
    val textColor = Color(0xFF000000) // Texto negro
    val cardBackgroundColor = Color(0xFFF5F5F5) // Fondo para los elementos agrupados
    var showDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Texto "OT" fijo en la parte superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(16.dp)
            ) {
                Text(
                    text = "R.P. ORIGEN",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = textColor
                    )
                )
            }

            // Contenido desplazable
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(cardBackgroundColor, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            "OT n°: 232323", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor
                            )
                        )

                        HorizontalDivider(
                            Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            "Origen", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor
                            )
                        )

                        Text(
                            "CA Maule PT", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal, fontSize = 16.sp, color = textColor
                            ), modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(
                            Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            "Comuna", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor
                            )
                        )

                        Text(
                            "Yerbas Buenas", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal, fontSize = 16.sp, color = textColor
                            ), modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(
                            Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            "Zona: ZONA 1", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor
                            )
                        )

                        HorizontalDivider(
                            Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            "TICKET ", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor
                            )
                        )

                        Text(
                            "0", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal, fontSize = 16.sp, color = textColor
                            ), modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(
                            Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            "FH Registro", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold, fontSize = 16.sp, color = textColor
                            )
                        )

                        Text(
                            "0", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal, fontSize = 16.sp, color = textColor
                            ), modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF74C42C), // Verde
                            contentColor = Color.White // Texto blanco
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(text = "GENERAR R.PRESENTACIÓN")
                    }
                }

                item {
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF74C42C), // Verde
                            contentColor = Color.White // Texto blanco
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(text = "CARGAR OT CONTINGENCIA")
                    }
                }



                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth() // Asegura que el Box ocupe todo el ancho
                            .padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = "SIM-F9154v1.3.0",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = textColor
                            ),
                            modifier = Modifier.align(Alignment.Center) // Centra el texto dentro del Box
                        )
                    }
                }


                item {
                    Spacer(modifier = Modifier.height(50.dp)) // Espacio extra después de los botones
                }
            }
        }
    }

    // AlertDialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Mensaje", fontWeight = FontWeight.Bold) },
            text = { Text("Registro Presentacion actualizado con exito") },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}
