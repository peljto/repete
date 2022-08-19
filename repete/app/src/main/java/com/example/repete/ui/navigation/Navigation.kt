package com.example.repete.ui.navigation

import Form
import HomePage
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreenNames.HomePage.route ){

        composable(route = AppScreenNames.HomePage.route){
            HomePage(navController = navController)
        }

        composable(route = AppScreenNames.Form.route){
            Form(navController = navController)
        }

    }

}