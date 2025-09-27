package com.example.truckease.screens

import com.google.android.gms.maps.model.CameraPosition

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun TrackingScreen() {
    var currentLocation by remember { mutableStateOf(LatLng(37.7749, -122.4194)) } // Default to San Francisco

    // Simulated location updates (in a real app, use GPS or API)
    LaunchedEffect(Unit) {
        simulateLocationUpdates { newLocation ->
            currentLocation = newLocation
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Live Truck Tracking",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(currentLocation, 12f)
                }
            ) {
                Marker(
                    state = MarkerState(position = currentLocation),
                    title = "Truck Location",
                    snippet = "Tracking in real-time"
                )
            }
        }
    }
}

/**
 * Simulates truck movement by slightly changing the latitude & longitude every second.
 */
@SuppressLint("MissingPermission")
private suspend fun simulateLocationUpdates(onLocationUpdate: (LatLng) -> Unit) {
    val startLocation = LatLng(37.7749, -122.4194) // San Francisco
    var lat = startLocation.latitude
    var lng = startLocation.longitude

    while (true) {
        lat += 0.001  // Simulate slight movement
        lng += 0.001
        onLocationUpdate(LatLng(lat, lng))
        kotlinx.coroutines.delay(1000) // Update every second
    }
}
