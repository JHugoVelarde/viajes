package com.example.viajes.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.viajes.ui.screens.DetailScr
import com.example.viajes.ui.screens.MainViajes
import com.example.viajes.ui.screens.ViajesViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        // 1. Pantalla de Inicio
        composable<HomeRoute> {
            val viewModel: ViajesViewModel = hiltViewModel()

            MainViajes(
                viewModel = viewModel,
                onDestinoClick = { destinoId ->
                    // Navegamos pasando el ID de forma segura
                    navController.navigate(DestinoDetailRoute(destinoId))
                }
            )
        }

        // 2. Pantalla de Detalle
        composable<DestinoDetailRoute> { backStackEntry ->
            // Extraemos el objeto de la ruta automáticamente
            val route = backStackEntry.toRoute<DestinoDetailRoute>()

            DetailScr(
                destinoId = route.destinoId,
                onBackClick = {
                    navController.navigateUp() // Volver atrás
                }
            )
        }
    }
}