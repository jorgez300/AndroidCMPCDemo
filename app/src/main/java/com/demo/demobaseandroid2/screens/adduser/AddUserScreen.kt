package com.demo.demobaseandroid2.screens.adduser

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.demo.demobaseandroid2.data.database.DatabaseProvider
import com.demo.demobaseandroid2.data.model.User
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var editingUser by remember { mutableStateOf<User?>(null) } // Usuario en edición
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userDao = DatabaseProvider.getDatabase(context).userDao()

    // Observa la lista de usuarios desde la base de datos
    val users by userDao.getAllUsers().collectAsState(initial = emptyList())

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
            if (username.isNotEmpty() && password.isNotEmpty()) {
                scope.launch {
                    try {
                        val user = User(username = username, password = password)
                        userDao.insert(user)
                        showToast(context, "User added successfully")
                        username = ""
                        password = ""
                    } catch (e: Exception) {
                        showToast(context, "Error adding user: ${e.message}")
                        Log.e("AddUserScreen", "Error inserting user", e)
                    }
                }
            } else {
                showToast(context, "Please fill all fields")
            }
        }) {
            Text("Add User")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Lista de usuarios
        Text(text = "Users List", modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(users) { user ->
                UserItem(
                    user = user,
                    onDelete = {
                        scope.launch {
                            userDao.delete(user)
                            showToast(context, "User deleted successfully")
                        }
                    },
                    onEdit = {
                        editingUser = user // Abre el diálogo de edición
                    }
                )
            }
        }
    }

    // Diálogo para editar usuarios
    if (editingUser != null) {
        EditUserDialog(
            user = editingUser!!,
            onDismiss = { editingUser = null },
            onSave = { updatedUser ->
                scope.launch {
                    userDao.update(updatedUser)
                    showToast(context, "User updated successfully")
                    editingUser = null
                }
            }
        )
    }
}

@Composable
fun UserItem(user: User, onDelete: () -> Unit, onEdit: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Username: ${user.username}")
            Text(text = "Password: ${user.password}")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = onEdit, modifier = Modifier.padding(end = 8.dp)) {
                    Text("Edit")
                }
                Button(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
fun EditUserDialog(user: User, onDismiss: () -> Unit, onSave: (User) -> Unit) {
    var username by remember { mutableStateOf(user.username) }
    var password by remember { mutableStateOf(user.password) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Edit User", style = MaterialTheme.typography.headlineSmall)
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
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        onSave(user.copy(username = username, password = password))
                    }) {
                        Text("Save")
                    }
                }
            }
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