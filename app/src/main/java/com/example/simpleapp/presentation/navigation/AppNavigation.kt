package com.example.simpleapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.simpleapp.domain.model.SimpleItem
import com.example.simpleapp.presentation.DetailsUI
import com.example.simpleapp.presentation.ListScreen
import com.example.simpleapp.presentation.MainScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainScreen) {
        composable<MainScreen> {
            MainScreen {
                navController.navigateOnce(ListScreen)
            }
        }

        composable<ListScreen> {
            ListScreen {
                navController.navigate(DetailsScreen(it))
            }
        }

        composable<DetailsScreen>(
            typeMap = mapOf(typeOf<SimpleItem>() to CustomNavigation)
        ) {
            val item = it.toRoute<DetailsScreen>()
            DetailsUI(
                item = item.item
            ) {
                navController.popBackStack()
            }
        }
    }
}

fun NavController.navigateOnce(route: Any) {
    val isCurrentDestination = currentDestination?.route == route
    if (!isCurrentDestination) {
        navigate(route)
    }
}


@Serializable
data object MainScreen

@Serializable
data object ListScreen

@Serializable
data class DetailsScreen(
    val item: SimpleItem
)