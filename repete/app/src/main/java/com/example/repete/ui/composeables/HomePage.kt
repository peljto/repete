import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.TextView
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
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.repete.R
import com.example.repete.ui.Screen
import com.example.repete.ui.theme.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage() {
    Scaffold(scaffoldState = rememberScaffoldState(),
        backgroundColor = eggshell,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ },
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

    ) {LazyColumn(modifier = Modifier.padding(bottom = 65.dp)){
        items(16) { index ->
            InstructionCard()
        }
    }}

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InstructionCard() {

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
                    Text(
                        text = "matisa"
                    )
                    Text(
                        text = "100kn/h"
                    )
                }
                Spacer(Modifier.weight(1f))
                ContactButton(expanded = expanded, onClick = { expanded = !expanded})
            }
            if(expanded) {
                Contact()
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
fun Contact(){
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
                context.dial(phone = "0989569108")
            }, colors = ButtonDefaults.buttonColors(backgroundColor = lightGreen),
                shape = RoundedCornerShape(20.dp)

                ) {
                Text(text = "0989569108", modifier = Modifier.background(lightGreen))
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

