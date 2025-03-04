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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvectorMainScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf(0) }

    val backgroundColor = Color(0xFF292E4A)
    val buttonColor = Color(0xFFE86B28)
    val textColor = Color(0xFFE9E4E7)
    val unselectedButtonColor = Color(0xFF3A3F5A)
    val borderColor = Color(0xFFE86B28)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Encabezado
            Header()

            Spacer(modifier = Modifier.height(24.dp))

            SegmentedControl(
                options = listOf(
                    "Programa día", "OT", "RP origen", "Cola camiones", "Checklist",
                    "GDE", "Destino", "Eventos"
                ),
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it },
                buttonColor = buttonColor,
                textColor = textColor,
                unselectedButtonColor = unselectedButtonColor,
                borderColor = borderColor
            )

            Spacer(modifier = Modifier.height(24.dp))

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

@Composable
fun Header() {
    val backgroundColor = Color(0xFF292E4A)
    val textColor = Color(0xFFE9E4E7)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Convector PT v.2.5",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = textColor
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Viaje no iniciado",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = textColor
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "JORQUERA TRANSPORTE S.A.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = textColor
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "FFXD30",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = textColor
            ),
            textAlign = TextAlign.Center
        )
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
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.dp) // Sin espacio entre botones
    ) {
        items(options.size) { index ->
            Button(
                onClick = { onOptionSelected(index) },
                modifier = Modifier
                    .width(100.dp) // Ancho fijo para cada botón
                    .padding(4.dp), // Padding reducido
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(
                    // Solo el botón seleccionado tendrá el color naranjo
                    containerColor = if (selectedOption == index) buttonColor else unselectedButtonColor,
                    contentColor = textColor
                ),
                contentPadding = PaddingValues(8.dp) // Padding interno reducido
            ) {
                Text(
                    text = options[index],
                    style = MaterialTheme.typography.bodySmall.copy( // Tamaño de fuente reducido
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp // Tamaño de fuente más pequeño
                    ),
                    maxLines = 1, // Asegura que el texto no se desborde
                    overflow = TextOverflow.Ellipsis // Añade puntos suspensivos si el texto es demasiado largo
                )
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
    // Contenido simple para "OT"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla OT",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
        )
    }
}

@Composable
fun RPOrigenScreen() {
    // Contenido simple para "RP origen"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla RP Origen",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
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
    // Contenido simple para "Checklist"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Checklist",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
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
    // Contenido simple para "Destino"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Destino",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color(0xFFE9E4E7)
            )
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
