package com.demo.demobaseandroid2.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.demobaseandroid2.core.navigation.Detail
import com.demo.demobaseandroid2.core.navigation.Home
import com.demo.demobaseandroid2.core.navigation.Setting


@Composable
fun LoginScreen(navigateTo: (Any) -> Unit, viewModel: LoginViewModel) {

    Body(navigateTo, viewModel)

}

@Preview(
    showBackground = true,
)
@Composable
fun LoginScreenPreview() {

    Body({}, LoginViewModel())

}


@Composable
fun Body(navigateTo: (Any) -> Unit, viewModel: LoginViewModel) {


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Formulario(navigateTo, viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        Etiquetas(navigateTo, viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        Botones(navigateTo, viewModel)
    }
}

@Composable
fun Etiquetas(navigateTo: (Any) -> Unit, viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = if (uiState.vip) Color.Yellow else Color.Green)
                .padding(20.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = uiState.email.toString(), fontSize = 25.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = uiState.password.toString(), fontSize = 25.sp)
        }
    }
}

@Composable
fun Botones(navigateTo: (Any) -> Unit, viewModel: LoginViewModel) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Red)
                .padding(20.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navigateTo(Home) }) {
                Text(text = "Navegar a la home")
            }

            Button(onClick = { navigateTo(Setting) }) {
                Text(text = "Navegar a la Setting")
            }

            Button(onClick = { navigateTo(Detail) }) {
                Text(text = "Navegar a Detail")
            }

        }
    }
}

@Composable
fun Formulario(navigateTo: (Any) -> Unit, viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .background(color = Color.Blue)
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = uiState.email.toString(),
                onValueChange = { viewModel.updateState(email = it) })
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = uiState.password.toString(),
                onValueChange = { viewModel.updateState(password = it) })
            Spacer(modifier = Modifier.height(20.dp))
            Checkbox(
                checked = uiState.vip,
                onCheckedChange = { viewModel.updateState(vip = it) }
            )
        }
    }

}

