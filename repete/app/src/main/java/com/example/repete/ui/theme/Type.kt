package com.example.repete.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.repete.R


val Nunito = FontFamily(
    Font(R.font.nunito_italic),
    Font(R.font.nunito_medium),
    Font(R.font.nunito_regular),
    Font(R.font.nunito_extra_light)
)
// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )


)