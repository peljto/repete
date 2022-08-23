package com.example.repete.ui.navigation

sealed class AppScreenNames(var route: String) {

    object Login: AppScreenNames("login")
    object SignUp: AppScreenNames("signup")
    object HomePage: AppScreenNames("home_page")
    object Form: AppScreenNames("form")
}
