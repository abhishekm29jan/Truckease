package com.example.truckease

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.truckease.screens.DashboardScreen
import com.example.truckease.screens.TrackingScreen
import com.example.truckease.screens.ProfileScreen
import com.example.truckease.ui.theme.TruckeaseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TruckeaseTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            NavHost(navController, startDestination = "dashboard") {
                composable("dashboard") { DashboardScreen(navController) }
                composable("tracking") { TrackingScreen() }
                composable("profile") { ProfileScreen(navController,LocalContext.current)}
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.LocalShipping, contentDescription = "Dashboard") },
            label = { Text("Dashboard") },
            selected = currentDestination == "dashboard",
            onClick = { navController.navigate("dashboard") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Tracking") },
            label = { Text("Tracking") },
            selected = currentDestination == "tracking",
            onClick = { navController.navigate("tracking") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentDestination == "profile",
            onClick = { navController.navigate("profile") }
        )
    }
}

data class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String)

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to TruckEase", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("tracking") }) {
            Text("Go to Tracking")
        }
    }
}

@Composable
fun TrackingScreen() {
    CenteredScreen(text = "Real-Time Tracking Coming Soon!")
}

@Composable
fun ProfileScreen(NavController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("User Profile", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { NavController.navigate("dashboard") }) {
            Text("Back to Dashboard")
        }
    }
}

@Composable
fun CenteredScreen(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text, fontSize = 20.sp)
    }
}
