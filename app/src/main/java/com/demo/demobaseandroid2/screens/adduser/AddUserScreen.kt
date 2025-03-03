package com.demo.demobaseandroid2.screens.adduser

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.demobaseandroid2.data.database.DatabaseProvider
import com.demo.demobaseandroid2.data.model.User
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen() {
    var username by remember { mutableStateOf("test_user") }
    var password by remember { mutableStateOf("123456") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userDao = DatabaseProvider.getDatabase(context).userDao()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add User")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                try {
                    val user = User(username = username, password = password)
                    userDao.insert(user)

                    // Limpiar el formulario después de insertar el usuario
                    username = ""
                    password = ""

                    showToast(context, "User added successfully")
                    Log.d("AddUserScreen", "User inserted: $username")
                } catch (e: Exception) {
                    showToast(context, "Error adding user: ${e.message}")
                    Log.e("AddUserScreen", "Error inserting user", e)
                }
            }
        }) {
            Text("Add Static User")
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun AddUserScreenPreview() {
    AddUserScreen()
}
