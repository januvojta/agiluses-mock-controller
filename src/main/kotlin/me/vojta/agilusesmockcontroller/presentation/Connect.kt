package me.vojta.agilusesmockcontroller.presentation.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Connect() {

    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column() {
            OutlinedTextField(value = "username", onValueChange = {})
            OutlinedTextField(value = "password", onValueChange = {})
        }
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Button(onClick = {},modifier = Modifier.padding(5.dp)) {
                Text("Connect")
            }
        }

    }
}