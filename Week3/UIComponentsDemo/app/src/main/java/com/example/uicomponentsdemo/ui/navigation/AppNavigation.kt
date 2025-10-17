package com.example.uicomponentsdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uicomponentsdemo.ui.screens.HomeScreen
import com.example.uicomponentsdemo.ui.screens.ImageScreen
import com.example.uicomponentsdemo.ui.screens.InputScreen
import com.example.uicomponentsdemo.ui.screens.LayoutScreen
import com.example.uicomponentsdemo.ui.screens.TextScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onTextClick = { navController.navigate("text") },
                onImageClick = { navController.navigate("image") },
                onInputClick = { navController.navigate("input") },
                onLayoutClick = { navController.navigate("layout") }
            )
        }
        composable("text") {
            TextScreen(onBackClick = { navController.popBackStack() })
        }
        composable("image") {
            ImageScreen(onBackClick = { navController.popBackStack() })
        }
        composable("input") {
            InputScreen(onBackClick = { navController.popBackStack() })
        }
        composable("layout") {
            LayoutScreen(onBackClick = { navController.popBackStack() })
        }
    }
}