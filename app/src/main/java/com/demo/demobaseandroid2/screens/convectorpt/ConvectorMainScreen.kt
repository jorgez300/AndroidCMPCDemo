import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.foundation.shape.CircleShape


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
                                .width(150.dp) // Aumentamos el ancho de los botones
                                .padding(4.dp), // Padding reducido
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(
                                // Solo el botón seleccionado tendrá el color naranjo
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


@Composable
fun ProgramaDiaScreen(navController: NavController) {
    // Contenido de la pantalla "Programa día"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Programa Día",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
        )
        // Aquí puedes agregar más contenido específico para "Programa día"
    }
}

@Composable
fun OTScreen() {
    val backgroundColor = Color(0xFFF5F5F5) // Fondo claro
    val textColor = Color(0xFF000000) // Texto negro

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
                    text = "OT N: XXXXXX",
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
                    .padding(horizontal = 16.dp)
            ) {
                items(1) { // Solo un ítem para el contenido
                    Column {

                        Text(
                            text = "Tipo Camión",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "PLANO-TRADICIONAL",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Conductor",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "17347460-1",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Producto",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Aserrada-Cross Cut-M.C.S4S",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Zona",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "ZONA 1",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Carro",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "GRFB98",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Inicio viaje",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "12-02-2025 10:00",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Origen",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "CA Maule PT",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Comuna",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Yerbas Buenas",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 0.5.dp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Llegada origen",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "12-02-2025 10:00",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Text(
                            text = "Servicio origen",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Destino",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Puerto mont pt",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Llegada destino",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "12-02-2025 16:00",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Button(
                            onClick = { /* Acción al confirmar OT */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF74C42C), // Verde
                                contentColor = Color.White // Texto blanco
                            ),
                            shape = RoundedCornerShape(4.dp) // Menos bordes redondeados
                        ) {
                            Text(text = "CONFIRMAR OT")
                        }

                        Text(
                            text = "SIM-F9153v1.3.0",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = textColor
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )


                    }
                }
                item {
                    Spacer(modifier = Modifier.height(50.dp)) // Espacio extra después de los botones
                }
            }
        }
    }
}

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

@Composable
fun ColaCamionesScreen() {
    // Contenido simple para "Cola camiones"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Cola Camiones",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
        )
    }
}

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
                    )
                ) {
                    Text("ENVIAR", color = buttonTextColor)
                }
                Button(
                    onClick = { responses.clear() },
                    modifier = Modifier.height(48.dp), // Botones más rectangulares
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor, // Fondo de los botones
                        contentColor = buttonTextColor // Texto blanco
                    )
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
                    text = "SIM-F9154v1.3.0",
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


@Composable
fun GDEScreen() {
    // Contenido simple para "GDE"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla GDE",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
        )
    }
}

@Composable
fun DestinoScreen() {
    val backgroundColor = Color(0xFFCDCACD)
    val textColor = Color(0xFF000000)
    val cardBackgroundColor = Color(0xFFE8E5E7)
    val headerTextColor = Color(0xFF2E3D50)
    val checkboxColor = Color(0xFFE56C29) // Color para el check
    val buttonColor = Color(0xFFBFBCBF)
    val buttonTextColor = Color(0xFF1F1D1F)
    var showDialog by remember { mutableStateOf(false) }
    val checklistItems = listOf(
        "Validar llegada Destino.",
        "Foto Respaldo.",
        "Recepcionado Por.",
        "Tarea Descarpe.", // Este es el único que tendrá los checkboxes
        "Termino de Viaje."
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
                    // Encabezado con información
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(cardBackgroundColor, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp),
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("OT N°: 243949", color = textColor)
                                Text("TRANSPORTE: 0", color = textColor)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Destino: Puerto Montt pt", color = textColor)
                                Text("Comuna: Puerto Montt", color = textColor)
                            }
                        }
                    }
                }

                item {
                    // Elementos de la lista
                    checklistItems.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(cardBackgroundColor, shape = RoundedCornerShape(12.dp))
                                .padding(16.dp)
                        ) {
                            Column {
                                // Icono de check antes del texto
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle, // Usamos el ícono de check redondo
                                        contentDescription = null,
                                        tint = checkboxColor,
                                        modifier = Modifier.size(54.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre ícono y texto
                                    Text(
                                        text = "${index + 1}. $item",
                                        color = textColor
                                    )
                                }
                                if (index == 3) { // Para el 4to ítem (Tarea Descarpe)
                                    Row(
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
                        // Separación entre elementos
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                item {
                    // Botones Enviar y Limpiar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { showDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor, // Fondo de los botones
                                contentColor = buttonTextColor // Texto blanco
                            )
                        ) {
                            Text("ENVIAR", color = buttonTextColor)
                        }
                        Button(
                            onClick = { responses.clear() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor, // Fondo de los botones
                                contentColor = buttonTextColor // Texto blanco
                            )
                        ) {
                            Text("LIMPIAR", color = buttonTextColor)
                        }
                    }
                }
            }
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


@Composable
fun EventosScreen() {
    // Contenido simple para "Eventos"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Eventos",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
        )
    }
}