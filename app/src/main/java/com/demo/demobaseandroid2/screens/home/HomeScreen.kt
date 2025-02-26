package com.demo.demobaseandroid2.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.demo.demobaseandroid2.core.navigation.Login
import com.demo.demobaseandroid2.core.navigation.Setting


@Composable
fun HomeScreen(navigateTo: (Any) -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "HOME SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateTo(Login) }) {
            Text(text = "Navegar a la login")
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateTo(Setting) }) {
            Text(text = "Navegar a la Setting")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}