import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.repete.ui.theme.bittersweetShimmer
import com.example.repete.ui.theme.lightGreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.repete.R
import com.example.repete.ui.theme.eggshell
import com.example.repete.ui.theme.green

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Form(navController : NavController) {

    Scaffold(
        backgroundColor = eggshell
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
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

                Column( verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    SelectSubject()
                    Spacer(modifier = Modifier.size(30.dp))
                    Price()
                    Spacer(modifier = Modifier.size(30.dp))
                    School()
                    Spacer(modifier = Modifier.size(30.dp))
                    Contact()
                    FloatingNextButton()
                }


            }
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectSubject() {
    val options = listOf(
        "Hrvatski jezik",
        "Engleski jezik",
        "Talijanski jezik",
        "Latinski jezik",
        "Matematika",
        "Fizika",
        "Kemija",
        "Povijest",
        "Zemljopis"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    var lastSelectedOption by remember { mutableStateOf(options[0]) }
    SideEffect {
        if(lastSelectedOption!=options[0]){
            lastSelectedOption = options[0]
        }

    }
    

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {  },
            label = { Text("subject") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            modifier = Modifier.padding(top = 20.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)

                }

            }
        }
    }
}


@Composable
fun Price() {
    var username by remember { mutableStateOf("") }

    Row(){
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("cijena u kunama") },
            singleLine = true,
        )
    }

}

@Composable
fun School() {
    val schoolList = arrayListOf("OŠ", "SŠ", "M")

    Row(horizontalArrangement = Arrangement.spacedBy(50.dp)){
        schoolList.forEach { option: String ->
          //  Spacer(modifier = Modifier.size(16.dp))
            Row {
                val isChecked = remember { mutableStateOf(false) }
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.Black,
                        checkedColor = bittersweetShimmer,
                    )
                )
              //  Spacer(modifier = Modifier.size(16.dp))
                Text(option)
            }
        }
    }

}

@Composable
fun Contact() {
    var username by remember { mutableStateOf("") }

    Column(){
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("unesite broj mobitela") },
            singleLine = true,
        )
    }

}
@Composable
fun FloatingNextButton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(onClick = {}, backgroundColor = bittersweetShimmer) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Edit")

        }

    }
}


