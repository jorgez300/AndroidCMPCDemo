package com.demo.demobaseandroid2.screens.gps

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

@Composable
fun LocationScreen(
    viewModel: LocationViewModel = viewModel()
) {
    var hasPermission by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasPermission = isGranted
        if (isGranted) {
            viewModel.startTracking()
        }
    }

    val location by viewModel.location.collectAsState()

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!hasPermission) {
            Text("Se necesitan permisos de ubicación")
        } else {
            location?.let { loc ->
                Text("Latitud: ${loc.latitude}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Longitud: ${loc.longitude}")
            } ?: Text("Obteniendo ubicación...")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (viewModel.location.value == null) {
                        viewModel.startTracking()
                    } else {
                        viewModel.stopTracking()
                    }
                }
            ) {
                Text(
                    if (viewModel.location.value == null)
                        "Iniciar seguimiento"
                    else
                        "Detener seguimiento"
                )
            }
        }
    }
}