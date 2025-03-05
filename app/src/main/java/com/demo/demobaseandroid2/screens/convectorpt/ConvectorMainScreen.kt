import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.navigation.NavController

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.foundation.shape.CircleShape


import com.demo.demobaseandroid2.screens.convectorpt.segmented.ProgramaDiaScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.DestinoScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.OTScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.RPOrigenScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.ColaCamionesScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.ChecklistScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.GDEScreen
import com.demo.demobaseandroid2.screens.convectorpt.segmented.EventosScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvectorMainScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(0) }

    val backgroundColor = Color(0xFF292E4A)
    val contentBackgroundColor = Color(0xFFCDCACD)
    val segmentedControlButtonColor = Color(0xFF292E4A)
    val buttonColor = Color(0xFFE86B28)
    val textColor = Color(0xFFFFFFFF)
    val borderColor = Color(0xFFE86B28)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {


            // Encabezado alineado a la izquierda
            Header()

            Spacer(modifier = Modifier.height(5.dp))

            SegmentedControl(
                options = listOf(
                    "PROGRAMA DIA", "OT", "RP ORIGEN", "COLA CAMIONES", "CHECKLIST",
                    "GDE", "DESTINO", "EVENTOS"
                ),
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it },
                buttonColor = buttonColor,
                textColor = textColor,
                unselectedButtonColor = segmentedControlButtonColor,
                borderColor = borderColor
            )

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(contentBackgroundColor),
                color = contentBackgroundColor
            ) {
                when (selectedOption) {
                    0 -> ProgramaDiaScreen(navController)
                    1 -> OTScreen()
                    2 -> RPOrigenScreen()
                    3 -> ColaCamionesScreen()
                    4 -> ChecklistScreen()
                    5 -> GDEScreen()
                    6 -> DestinoScreen()
                    7 -> EventosScreen()
                }
            }
        }
    }
}

@Composable
fun Header() {
    val backgroundColor = Color(0xFF292E4A)
    val textColor = Color(0xFFE9E4E7)
    val orangeColor = Color(0xFFFF8C00) // Color naranja para el borde

    // Usamos un Box para la disposición del icono del camión y el círculo con borde
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 8.dp), // Padding horizontal y vertical general
        horizontalArrangement = Arrangement.SpaceBetween, // Espaciado entre los elementos
        verticalAlignment = Alignment.CenterVertically // Alineación vertical del contenido
    ) {
        // Columna con los textos
        Column(
            modifier = Modifier.weight(1f) // La columna ocupa el espacio disponible
        ) {
            Spacer(modifier = Modifier.height(25.dp)) // Margen superior de 25dp

            Text(
                text = "Convector PT v.2.5",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = textColor
                ),
                textAlign = TextAlign.Start // Alinea el texto a la izquierda
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Icono de camión rojo antes del texto
                Icon(
                    imageVector = Icons.Default.Home, // Usamos un ícono genérico de coche, pero puedes usar otro
                    contentDescription = "Camión",
                    tint = Color.Red, // Tint de color rojo
                    modifier = Modifier.size(24.dp) // Tamaño del ícono
                )

                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el ícono y el texto

                Text(
                    text = "Viaje no iniciado",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = textColor
                    ),
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = "JORQUERA TRANSPORTE S.A.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = textColor
                ),
                textAlign = TextAlign.Start
            )

            Text(
                text = "FFXD30",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = textColor
                ),
                textAlign = TextAlign.Start
            )
        }

        // Círculo con borde naranja al costado derecho
        Box(
            modifier = Modifier
                .size(60.dp) // Tamaño del círculo
                .border(2.dp, orangeColor, shape = CircleShape) // Borde naranja
                .padding(4.dp), // Espaciado interno para el borde
            contentAlignment = Alignment.Center
        ) {
            // Puedes poner contenido dentro del círculo si lo deseas
        }
    }
}

@Composable
fun SegmentedControl(
    options: List<String>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
    buttonColor: Color, // Color para el botón seleccionado
    textColor: Color, // Color del texto
    unselectedButtonColor: Color, // Color de fondo para los botones no seleccionados
    borderColor: Color // Color del borde (ya no se usará)
) {
    val pageSize = 3 // Mostrar 3 opciones por pantalla
    val pages = (options.size + pageSize - 1) / pageSize
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre botones
    ) {
        items(pages) { page ->
            Row(
                modifier = Modifier.fillParentMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0 until pageSize) {
                    val index = page * pageSize + i
                    if (index < options.size) {
                        Button(
                            onClick = { onOptionSelected(index) },
                            modifier = Modifier
                                .width(if (options[index] == "OT") 91.dp else 130.dp) // 30% menos para "OT"
                                .padding(4.dp),
                            shape = if (selectedOption == index) {
                                RoundedCornerShape(16.dp) // Redondeo más grande cuando está seleccionado
                            } else {
                                RoundedCornerShape(8.dp) // Redondeo más pequeño cuando no está seleccionado
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == index) buttonColor else unselectedButtonColor,
                                contentColor = if (selectedOption == index) Color.Black else Color.White // Texto negro o blanco
                            )
                        ) {
                            Text(
                                text = options[index],
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp // Tamaño de fuente reducido
                                ),
                                maxLines = 1, // Asegura que el texto no se desborde
                                overflow = TextOverflow.Ellipsis // Añade puntos suspensivos si el texto es demasiado largo
                            )
                        }

                    } else {
                        Spacer(modifier = Modifier.weight(1f)) // Espacio vacío si no hay suficientes opciones
                    }
                }
            }
        }
    }
}


