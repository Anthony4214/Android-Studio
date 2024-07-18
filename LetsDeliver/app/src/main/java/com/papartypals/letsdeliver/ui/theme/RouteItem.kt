package com.papartypals.letsdeliver.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.papartypals.letsdeliver.data.Route
import com.papartypals.letsdeliver.data.Stop
import com.papartypals.letsdeliver.data.Worker

@Composable
fun RouteItem(route: Route, onMoveStop: (Stop, Route) -> Unit, onAssignWorker: (Worker) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(1.dp, Color.Gray)
    ) {
        Text("Route ID: ${route.id}", style = MaterialTheme.typography.h6)
        Text("Stops:")
        route.stops.forEach { stop ->
            Text("- ${stop.address}")
        }
        Button(onClick = {
            // Handle assign route to worker
            onAssignWorker(Worker("Worker1"))
        }) {
            Text("Assign to Worker")
        }
        // Additional logic for moving stops between routes
    }
}