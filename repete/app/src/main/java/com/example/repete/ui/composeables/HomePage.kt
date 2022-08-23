import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.repete.R
import com.example.repete.model.Oglas
import com.example.repete.repository.Resources
import com.example.repete.ui.composeables.viewmodel.HomeUiState
import com.example.repete.ui.composeables.viewmodel.HomeViewModel
import com.example.repete.ui.theme.*
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(homeViewModel: HomeViewModel?, navController : NavController) {

    val homeUiState = homeViewModel?.homeUiState ?: HomeUiState()

    LaunchedEffect(key1 = Unit){
        homeViewModel?.loadOglas()
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = bittersweetShimmer),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(R.drawable.ic_school), contentDescription = null, modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp))
                Text(text = "repete", style = MaterialTheme.typography.h1)
                IconButton(onClick = {
                    homeViewModel?.signOut()
                    navController.navigate("login")
                }) {
                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.padding(start = 200.dp))

                }
            }
        },
        scaffoldState = rememberScaffoldState(),
        backgroundColor = eggshell,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate("form")},
                backgroundColor = bittersweetShimmer,
                elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp),
            contentColor = Color.Black
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add button")
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = darkGreen,
                cutoutShape = CircleShape,
                contentPadding = PaddingValues(horizontal = 50.dp),
                elevation = 2.dp
            ){}
        }

    ) {
        when(homeUiState.oglasList){
            is Resources.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is Resources.Success -> {
                LazyColumn(modifier = Modifier.padding(bottom = 65.dp)){
                    items(homeUiState.oglasList.data ?: emptyList()) { oglas ->
                        InstructionCard(oglas = oglas)

                    }
                }

            }
            else -> {
                Text(text = homeUiState.oglasList.throwable?.localizedMessage ?: "Uknowmn error", color = Color.Red)
            }
        }

    }

    LaunchedEffect(key1 = homeViewModel?.hasUser) {
        if(homeViewModel?.hasUser == false){
            navController.navigate("login")
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InstructionCard(oglas: Oglas) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        onClick = {},
        modifier = Modifier.padding(8.dp),
        backgroundColor = green
    ){
        Column(
            modifier = Modifier.animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column() {
                    Text(text = oglas.subject,)
                    Text(text = oglas.typeOfEducation)
                    Text(text = oglas.price)

                    Text(text = formateDate(oglas.timestamp))
                }
                Spacer(Modifier.weight(1f))
                ContactButton(expanded = expanded, onClick = { expanded = !expanded})
            }
            if(expanded) {
                MoreInformation(oglas = oglas)
            }
            }

        }
    }



@Composable
fun ContactButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick,
    modifier = Modifier.padding(start = 200.dp)) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = "See more information about accident",
        )

    }
}

@Composable
fun MoreInformation(oglas: Oglas){
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {

            Button(onClick = {
                context.dial(phone = oglas.contact)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = lightGreen),
                shape = RoundedCornerShape(20.dp)

                ) {
                Text(text = oglas.contact, modifier = Modifier.background(lightGreen))
            }

        }


        }
}




fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}

private fun formateDate(timestamp: Timestamp):String {
    val sdf = SimpleDateFormat("MM-dd-yy hh:mm", Locale.getDefault())
    return sdf.format(timestamp.toDate())
}
