package com.demo.demobaseandroid2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.demo.demobaseandroid2.core.navigation.NavigationHelper
import com.demo.demobaseandroid2.domain.helper.LocationManager
import com.demo.demobaseandroid2.ui.theme.DemoBaseAndroid2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoBaseAndroid2Theme {
                NavigationHelper(this)
            }
        }
    }
}

