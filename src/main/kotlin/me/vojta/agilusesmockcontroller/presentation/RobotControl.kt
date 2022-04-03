package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.vojta.agilusesmockcontroller.data.Robot

@Composable
fun RobotControl(robot: Robot) {
    val activeOperation = remember { mutableStateOf<String?>(null) }

    robot.onActiveOperationChange = {
        activeOperation.value = it
    }

    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column(modifier = Modifier.width(500.dp)) {
            Row {
                Text(
                    text = robot.name,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .weight(8f))
                Text(
                    text = if (activeOperation.value.isNullOrBlank()) "Inactive" else "Active",
                    color = if (activeOperation.value.isNullOrBlank()) Color.Red else Color.Green,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(2f)
                )
            }
            Row {
                Text(activeOperation.value ?: "None")
            }
        }
        Column {
            Button(onClick = {}, modifier = Modifier.padding(7.dp).requiredWidth(150.dp)) {
                Text("Run Random")
            }
        }
    }
    Divider()
}