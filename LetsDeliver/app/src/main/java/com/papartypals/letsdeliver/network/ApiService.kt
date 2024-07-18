package com.papartypals.letsdeliver.network

import com.papartypals.letsdeliver.data.*
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

object ApiService {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun fetchTrucksFromAPI(): List<Truck> {
        // Replace with actual API call
        return listOf(
            Truck("Truck1", "TRK123"),
            Truck("Truck2", "TRK456")
        )
    }

    suspend fun fetchRoutesFromAPI(): List<Route> {
        // Replace with actual API call
        return listOf(
            Route("Route1", listOf(Stop("Stop1", "Address1"), Stop("Stop2", "Address2"))),
            Route("Route2", listOf(Stop("Stop3", "Address3"), Stop("Stop4", "Address4")))
        )
    }

    suspend fun fetchWorkersFromAPI(): List<Worker> {
        // Replace with actual API call
        return listOf(
            Worker("Worker1"),
            Worker("Worker2")
        )
    }
}