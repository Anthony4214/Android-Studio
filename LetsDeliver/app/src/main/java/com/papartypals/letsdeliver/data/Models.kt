package com.papartypals.letsdeliver.data

data class Truck(val name: String, val id: String)
data class Route(val id: String, val stops: List<Stop>)
data class Stop(val name: String, val address: String)
data class Worker(val name: String)