package com.papartypals.letsdeliver.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.papartypals.letsdeliver.data.*
import com.papartypals.letsdeliver.network.ApiService
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var trucks by remember { mutableStateOf(listOf<Truck>()) }
    var routes by remember { mutableStateOf(listOf<Route>()) }
    var workers by remember { mutableStateOf(listOf<Worker>()) }
    var selectedTruck by remember { mutableStateOf<Truck?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Fetch trucks, routes, and workers from the API
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            trucks = ApiService.fetchTrucksFromAPI()
            routes = ApiService.fetchRoutesFromAPI()
            workers = ApiService.fetchWorkersFromAPI()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Select a Truck", style = MaterialTheme.typography.h5)
        DropdownMenu(
            expanded = selectedTruck != null,
            onDismissRequest = { selectedTruck = null },
            modifier = Modifier.fillMaxWidth()
        ) {
            trucks.forEach { truck ->
                DropdownMenuItem(onClick = { selectedTruck = truck }) {
                    Text(truck.name)
                }
            }
        }

        selectedTruck?.let { truck ->
            Text("Routes for ${truck.name}", style = MaterialTheme.typography.h6)
            LazyColumn {
                items(routes) { route ->
                    RouteItem(route = route, onMoveStop = { stop, newRoute ->
                        // Handle moving stop to new route
                    }, onAssignWorker = { worker ->
                        // Handle assigning route to worker
                    })
                }
            }
        }
    }
}