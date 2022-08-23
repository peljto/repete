package com.example.repete.ui.navigation

import Form
import HomePage
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.repete.ui.composeables.LogInScreen
import com.example.repete.ui.composeables.SignInScreen
import com.example.repete.ui.composeables.viewmodel.FormViewModel
import com.example.repete.ui.composeables.viewmodel.HomeViewModel
import com.example.repete.ui.composeables.viewmodel.LogInViewModel

@Composable
fun Navigation(logInViewModel: LogInViewModel, homeViewModel: HomeViewModel, formViewModel: FormViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreenNames.Login.route ){

        composable(route = AppScreenNames.Login.route){
            LogInScreen(logInViewModel = logInViewModel, navController = navController)
        }

        composable(route = AppScreenNames.SignUp.route){
            SignInScreen(logInViewModel = logInViewModel, navController = navController)
        }

        composable(route = AppScreenNames.HomePage.route){
            HomePage(navController = navController, homeViewModel = homeViewModel)
        }

        composable(route = AppScreenNames.Form.route){
            Form(navController = navController, formViewModel = formViewModel)
        }

    }

}