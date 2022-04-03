package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RobotControl(){
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column() {
            Row {
                Text("name of robot")
                Text("state of robot")

            }
            Row {
                Text("operation running")
            }
        }
        Column {
            Button(onClick = {}, modifier = Modifier.padding(5.dp)){
                Text("Run")
            }
        }

    }
    Divider()
}