package com.example.repete.ui.navigation

sealed class AppScreenNames(var route: String) {

    object HomePage: AppScreenNames("home_page")
    object Form: AppScreenNames("form")
}
