package com.example.repete
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.repete.ui.composeables.viewmodel.FormViewModel
import com.example.repete.ui.composeables.viewmodel.HomeViewModel
import com.example.repete.ui.composeables.viewmodel.LogInViewModel
import com.example.repete.ui.navigation.Navigation

import com.example.repete.ui.theme.RepeteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val logInViewModel = viewModel(modelClass = LogInViewModel::class.java)
            val formViewModel = viewModel(modelClass = FormViewModel::class.java)
            val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
            RepeteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Navigation(logInViewModel = logInViewModel, homeViewModel = homeViewModel, formViewModel = formViewModel)
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RepeteTheme {

    }
}