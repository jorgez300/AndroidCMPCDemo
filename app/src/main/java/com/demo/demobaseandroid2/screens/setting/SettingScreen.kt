package com.demo.demobaseandroid2.screens.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.demo.demobaseandroid2.core.navigation.Back


@Composable
fun SettingScreen(navigateTo: (Any) -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "LOGIN SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateTo(Back) }) {
            Text(text = "Navegar atras")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}