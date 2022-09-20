package dev.arthurdamous.androidwpp

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.arthurdamous.androidwpp.feature_chat.presentation.ChatScreen
import dev.arthurdamous.androidwpp.feature_login.presentation.LoginScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccessful = {
                    navController.popBackStack()
                    navController.navigate(
                        route = "chat"
                    )
                }
            )
        }
        composable("chat") {
            ChatScreen()
        }
    }

}