package letsdeliver

// Define a DeliveryApp class
class DeliveryApp {
    private val subscriptionPricePerUser = 10 // Subscription price per additional user/device
    private val mapService: MapService = GoogleMapService() // Integration with Google Maps

    fun calculateRoute(user: User, destinations: List<String>, numberOfVehicles: Int) {
        if (user.isSubscribed || !user.hasReachedDeviceLimit()) {
            val deliveryRoute = mapService.calculateBestRoute(destinations)
            val splitRoutes = splitRoute(deliveryRoute, numberOfVehicles)
            assignRoutesToUsers(splitRoutes, user)
        } else {
            println("Please subscribe to access this feature.")
        }
    }

    private fun splitRoute(route: List<String>, numberOfVehicles: Int): List<List<String>> {
        // Logic to split the route into specified number of delivery vehicles
        val splitRoutes = mutableListOf<List<String>>()
        val routeSize = route.size
        val batchSize = routeSize / numberOfVehicles
        var startIndex = 0
        var endIndex: Int

        for (i in 0 until numberOfVehicles) {
            endIndex = if (i == numberOfVehicles - 1) routeSize else startIndex + batchSize
            splitRoutes.add(route.subList(startIndex, endIndex))
            startIndex = endIndex
        }

        return splitRoutes
    }

    private fun assignRoutesToUsers(splitRoutes: List<List<String>>, user: User) {
        // Logic to assign routes to other users within the company
        for ((index, route) in splitRoutes.withIndex()) {
            if (index == 0) {
                user.assignRoute(route)
                println("Assigned route to ${user.name}: $route")
            } else {
                val newUser = User("User ${index + 1}")
                newUser.assignRoute(route)
                println("Assigned route to ${newUser.name}: $route")
            }
        }
    }
}

// Define a User class
data class User(val name: String) {
    var isSubscribed: Boolean = false
    private var deviceCount: Int = 1

    fun hasReachedDeviceLimit(): Boolean {
        return deviceCount >= 2
    }

    fun assignRoute(route: List<String>) {
        // Logic to assign route to the user
        println("$name has been assigned the route: $route")
    }
}

// Define a MapService interface
interface MapService {
    fun calculateBestRoute(destinations: List<String>): List<String>
}

// Implement GoogleMapService class
class GoogleMapService : MapService {
    override fun calculateBestRoute(destinations: List<String>): List<String> {
        // Logic to calculate the best delivery route using Google Maps
        return destinations.shuffled()
    }
}

// Implement WazeMapService class
class WazeMapService : MapService {
    override fun calculateBestRoute(destinations: List<String>): List<String> {
        // Logic to calculate the best delivery route using Waze
        return destinations.shuffled()
    }
}

// Example usage of the DeliveryApp
fun main() {
    val deliveryApp = DeliveryApp()
    val user = User("John Doe")
    user.isSubscribed = true

    val destinations = listOf("Destination A", "Destination B", "Destination C", "Destination D")
    val numberOfVehicles = 2

    deliveryApp.calculateRoute(user, destinations, numberOfVehicles)
}
