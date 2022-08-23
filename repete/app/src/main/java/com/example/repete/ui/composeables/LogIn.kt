package com.example.repete.ui.composeables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.repete.ui.composeables.viewmodel.LogInViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.repete.ui.theme.bittersweetShimmer
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LogInScreen(
    logInViewModel: LogInViewModel? = null,
    navController : NavController
) {
    val loginUiState = logInViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.h1,
            color = bittersweetShimmer,
        )

        if(isError) {
            Text(text = loginUiState?.loginError ?: "unknown error", color = Color.Red)
        }
        
        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.userName ?: "",
            onValueChange = {logInViewModel?.onUserNameChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                ) },
            label = {
                Text(text = "Email")
            },
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.password ?: "",
            onValueChange = {logInViewModel?.onPasswordNameChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock,
                    contentDescription = null)
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Button(onClick = {logInViewModel?.loginUser(context) }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an Account?")
            //Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = {navController.navigate("signup")}, modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "SignUp")
                }
            }



        if(loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = logInViewModel?.hasUser) {
            if(logInViewModel?.hasUser == true){
                navController.navigate("home_page")
            }
        }

    }


}


@Composable
fun SignInScreen(
    logInViewModel: LogInViewModel? = null,
    navController : NavController
) {
    val loginUiState = logInViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.h1,
            color = bittersweetShimmer,

            )
        if(isError) {
            Text(text = loginUiState?.signUpError ?: "unknown error", color = Color.Red)
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.userNameSignUp ?: "",
            onValueChange = {logInViewModel?.onUserNameChangeSignUp(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = null)
            },
            label = {
                Text(text = "Email")
            },
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.passwordSignUp ?: "",
            onValueChange = {logInViewModel?.onPasswordChangeSignUp(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock,
                    contentDescription = null)
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.confirmPasswordSignUp ?: "",
            onValueChange = {logInViewModel?.onConfrimPasswordChange(it)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock,
                    contentDescription = null)
            },
            label = {
                Text(text = "confirm Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )


        Button(onClick = {logInViewModel?.createUser(context) }) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an Account?")
            //Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = {navController.navigate("login")}, modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "Login")
                }
            }
        if(loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = logInViewModel?.hasUser) {
            if(logInViewModel?.hasUser == true){
                navController.navigate("home_page")
            }
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {
    RepeteTheme {
       LogInScreen( logInViewModel = LogInViewModel(),
        /*navController = navController*/)
    }
}*/