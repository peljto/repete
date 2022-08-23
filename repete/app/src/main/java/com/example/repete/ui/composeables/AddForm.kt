import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.repete.ui.theme.bittersweetShimmer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.repete.ui.composeables.viewmodel.FormUiState
import com.example.repete.ui.composeables.viewmodel.FormViewModel
import com.example.repete.ui.theme.RepeteTheme
import com.example.repete.ui.theme.eggshell
import com.example.repete.ui.theme.green
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Form(navController : NavController, formViewModel: FormViewModel?) {

    val formUiState = formViewModel?.formUiState ?: FormUiState()

    val isFormsNotBlank = formUiState.subject.isNotBlank() &&
            formUiState.typeOfEducation.isNotBlank()


    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = eggshell,
        scaffoldState = scaffoldState,
        topBar = {
            FormTopBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            if(formUiState.oglasAddedStatus){
                scope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("Added oglas successfully")
                    formViewModel?.resetOglasAddedStatus()
                    navController.navigate("home_page")
                }
            }

            Card(
                backgroundColor = green,
                elevation = 4.dp,
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 100.dp,
                        bottom = 100.dp
                    )
            ) {
                Column() {
                    OutlinedTextField(
                        value = formUiState.subject,
                        onValueChange = {
                            formViewModel?.onSubjectChange((it))
                        },
                        label = {Text(text = "subject")},
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = formUiState.typeOfEducation,
                        onValueChange = {
                            formViewModel?.onTypeOfEducationChange((it))
                        },
                        label = {Text(text="type of education")},
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = formUiState.price,
                        onValueChange = {
                            formViewModel?.onPriceChange((it))
                        },
                        label = {Text(text="price in hrk")},
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = formUiState.contact,
                        onValueChange = {
                            formViewModel?.onContactChange((it))
                        },
                        label = {Text(text="phone number")},
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.size(60.dp))

                    FloatingNextButton(formViewModel = formViewModel)

                }
                }



            }
        }


    }


@Composable
fun FloatingNextButton( formViewModel: FormViewModel?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = {
                      formViewModel?.addOglas()
            }, backgroundColor = bittersweetShimmer) {
            Icon(Icons.Filled.ArrowForward, contentDescription = null)

        }

    }
}

@Composable
fun FormTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text("Novi oglas")
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigate("home_page")}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
                
            }
        },
        backgroundColor = bittersweetShimmer
    ) 
}


@Preview
@Composable
fun PrevFormScreen() {
    RepeteTheme() {
        val navController = rememberNavController()
        Form(navController = navController, formViewModel = null)
        
    }
}
