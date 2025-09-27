package com.example.truckease.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome Header
        Text(
            text = "Welcome to TruckEase",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Manage your shipments and track your trucks easily.")

        Spacer(modifier = Modifier.height(16.dp))

        // Live Shipment Status
        ShipmentStatusCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Actions
        QuickActions(navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Recent Shipments List
        RecentShipments()
    }
}

@Composable
fun ShipmentStatusCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Light Blue
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Live Shipment Status",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "3 trucks in transit, 2 waiting for dispatch")
        }
    }
}

@Composable
fun QuickActions(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        QuickActionButton(
            label = "Track Trucks",
            icon = Icons.Filled.LocationOn,
            onClick = { navController.navigate("tracking") }
        )
        QuickActionButton(
            label = "Manage Drivers",
            icon = Icons.Filled.Person,
            onClick = { navController.navigate("profile") }
        )
    }
}

@Composable
fun QuickActionButton(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(140.dp, 50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = label)
            Spacer(modifier = Modifier.width(8.dp))
            Text(label)
        }
    }
}

@Composable
fun RecentShipments() {
    Column {
        Text(
            text = "Recent Shipments",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        val shipments = listOf(
            "Order #12345 - Delivered",
            "Order #12346 - In Transit",
            "Order #12347 - Waiting for Pickup"
        )

        shipments.forEach { shipment ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)) // Light Green
            ) {
                Text(
                    text = shipment,
                    modifier = Modifier.padding(12.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}
