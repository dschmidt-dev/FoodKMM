package dev.dschmidt.foodkmm.android.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dschmidt.foodkmm.Greeting

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            Column {
                Text(Greeting().greeting())
                Divider()
                Button( onClick = {navController.navigate(Screen.RecipeDetail.route)}) {
                    Text("Go To Detail")
                }
            }
        }
        composable(route = Screen.RecipeDetail.route) { navBackStackEntry ->
            Column {
                Text("DETAIL ")
            }
        }
    }
}