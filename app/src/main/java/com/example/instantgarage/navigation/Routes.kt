package com.example.instantgarage.navigation

sealed class Routes(val route: String) {

    data object Home : Routes("home")

    data object MechanicDetails : Routes("mechanic_details/{mechanicId}") {

        fun createRoute(mechanicId: Int): String {
            return "mechanic_details/$mechanicId"
        }
    }

    data object ServiceRequest : Routes("service_request")
}