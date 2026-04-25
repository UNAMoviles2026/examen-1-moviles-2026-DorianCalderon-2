package com.moviles.examenmoviles.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviles.examenmoviles.ui.screens.FavoritesScreen
import com.moviles.examenmoviles.ui.screens.SettingsScreen
import com.moviles.examenmoviles.ui.screens.SpaceDetailScreen
import com.moviles.examenmoviles.ui.screens.SpacesListScreen
import com.moviles.examenmoviles.viewmodel.SpacesViewModel

/**
 * Sealed class defining navigation routes for the application
 */
sealed class Screen(val route: String) {
    object SpacesList : Screen("spaces_list")
    object SpaceDetail : Screen("space_detail/{spaceId}") {
        fun createRoute(spaceId: String) = "space_detail/$spaceId"
    }
    object Favorites : Screen("favorites")
    object Settings : Screen("settings")
}

/**
 * Navigation graph for the application
 * Manages all navigation between screens
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: SpacesViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SpacesList.route
    ) {
        // Spaces List Screen
        composable(Screen.SpacesList.route) {
            SpacesListScreen(
                viewModel = viewModel,
                onSpaceClick = { spaceId ->
                    navController.navigate(Screen.SpaceDetail.createRoute(spaceId))
                },
                onNavigateToScreen = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.SpacesList.route)
                    }
                }
            )
        }

        // Space Detail Screen
        composable(
            route = Screen.SpaceDetail.route,
            arguments = listOf(
                navArgument("spaceId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val spaceId = backStackEntry.arguments?.getString("spaceId") ?: return@composable
            SpaceDetailScreen(
                spaceId = spaceId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToScreen = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.SpacesList.route)
                    }
                }
            )
        }

        // Favorites Screen (placeholder)
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onNavigateToScreen = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.SpacesList.route)
                    }
                }
            )
        }

        // Settings Screen (placeholder)
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateToScreen = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.SpacesList.route)
                    }
                }
            )
        }
    }
}

