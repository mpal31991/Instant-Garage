package com.example.instantgarage.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instantgarage.ui.home.HomeScreen
import com.example.instantgarage.ui.mechanic.MechanicDetailsScreen
import com.example.instantgarage.ui.service.ServiceRequestScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {

        composable(Routes.Home.route) {
            HomeScreen(
                onMechanicClick = { mechanicId ->
                    navController.navigate(
                        Routes.MechanicDetails.createRoute(mechanicId)
                    )
                }
            )
        }

        composable(
            route = Routes.MechanicDetails.route,
            arguments = listOf(
                navArgument("mechanicId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val mechanicId = backStackEntry.arguments?.getInt("mechanicId")
            mechanicId?.let { mechanicId ->
                MechanicDetailsScreen(
                    mechanicId = mechanicId,
                    navController = navController
                )
            }
        }

        composable(Routes.ServiceRequest.route) {
            ServiceRequestScreen(
                navController = navController
            )
        }
    }
}