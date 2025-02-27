//package com.demo.demobaseandroid2.screens.listuser

package com.demo.demobaseandroid2.screens.listuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.demo.demobaseandroid2.data.database.DatabaseProvider
import com.demo.demobaseandroid2.data.model.User

@Composable
fun ListUserScreen() {
    val context = LocalContext.current
    val userDao = DatabaseProvider.getDatabase(context).userDao()
    val users: List<User> by userDao.getAllUsers().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "List of Users")
        LazyColumn {
            items(users) { user ->
                Text(text = "Username: ${user.username}, Password: ${user.password}")
            }
        }
    }
}