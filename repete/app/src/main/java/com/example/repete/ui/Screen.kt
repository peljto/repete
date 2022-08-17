package com.example.repete.ui

import androidx.annotation.DrawableRes
import com.example.repete.R

sealed class Screen(
    val id: Int,
    val title: String,
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unSelectedIconId: Int
) {
    object Add: Screen(id = 0, title = "Home", selectedIconId = R.drawable.ic_add, unSelectedIconId = R.drawable.ic_add)
}
