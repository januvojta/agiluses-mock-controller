package me.vojta.agilusesmockcontroller.presentation.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.vojta.agilusesmockcontroller.domain.DomainFacade

@Composable
fun Connect(domain: DomainFacade) {

    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column(modifier = Modifier.width(500.dp)) {
            OutlinedTextField(value = "username", onValueChange = {},modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = "password", onValueChange = {},modifier = Modifier.fillMaxWidth())
        }
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Button(onClick = {},modifier = Modifier.padding(7.dp).requiredHeight(120.dp).requiredWidth(100.dp)) {
                Text("Connect")
            }
        }

    }
}