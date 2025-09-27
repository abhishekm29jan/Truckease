package com.example.truckease.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, context: Context) {
    val scope = rememberCoroutineScope()

    // State variables for user profile data
    var name by remember { mutableStateOf(TextFieldValue("Abhishek Mishra")) }
    var email by remember { mutableStateOf(TextFieldValue("abhishekm29jan@gmail.com")) }
    var phone by remember { mutableStateOf(TextFieldValue("8480342367")) }
    var truckID by remember { mutableStateOf(TextFieldValue("OD 02BH5645")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Management", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Name Input
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        // Phone Input
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        // Truck ID Input
        OutlinedTextField(
            value = truckID,
            onValueChange = { truckID = it },
            label = { Text("Truck ID") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                scope.launch {
                    Toast.makeText(context, "Profile Updated!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Save Profile")
        }

        // Logout Button
        Button(
            onClick = {
                navController.navigate("login") // Replace with actual login screen
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
        ) {
            Text("Logout")
        }
    }
}
