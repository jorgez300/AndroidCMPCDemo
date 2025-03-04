package com.demo.demobaseandroid2.screens.convectorpt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.demo.demobaseandroid2.core.navigation.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvectorPTScreen(navigateTo: (Any) -> Unit) {
    var patente by remember { mutableStateOf(TextFieldValue("")) }

    // Colores personalizados
    val backgroundColor = Color(0xFF292E4A) // Fondo: #292E4A
    val buttonColor = Color(0xFFE86B28) // Botón: #E86B28
    val textColor = Color(0xFFE9E4E7) // Fuentes: #E9E4E7

    // Surface para aplicar el fondo
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título "Bienvenido a"
            Text(
                text = "Bienvenido a",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    color = textColor // Aplicar color de fuente
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Título "Convector PT (V.2.5)"
            Text(
                text = "Convector PT (V.2.5)",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = TextUnit(20f, TextUnitType.Sp),
                    color = textColor // Aplicar color de fuente
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Instrucción "Ingresar patente para comenzar"
            Text(
                text = "Ingresar patente para comenzar",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    color = textColor // Aplicar color de fuente
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para ingresar la patente
            OutlinedTextField(
                value = patente,
                onValueChange = { patente = it },
                label = { Text("Patente", color = textColor) }, // Color del label
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = buttonColor, // Borde enfocado
                    unfocusedBorderColor = textColor.copy(alpha = 0.5f), // Borde no enfocado
                    cursorColor = textColor, // Color del cursor
                    focusedLabelColor = textColor, // Color del label cuando está enfocado
                    unfocusedLabelColor = textColor.copy(alpha = 0.5f), // Color del label cuando no está enfocado
                    //  textColor = textColor // Color del texto ingresado
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón "INGRESAR PATENTE"
            Button(
                onClick = {
                    if (patente.text.isNotEmpty()) {
                        navigateTo(Home) // Navega a la pantalla principal
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor, // Color del botón
                    contentColor = textColor // Color del texto del botón
                )
            ) {
                Text(
                    text = "INGRESAR PATENTE",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(16f, TextUnitType.Sp)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto "SIM-F9150 (v1.3.0)"
            Text(
                text = "SIM-F9150 (v1.3.0)",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = TextUnit(12f, TextUnitType.Sp),
                    color = textColor.copy(alpha = 0.8f) // Color con transparencia
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}