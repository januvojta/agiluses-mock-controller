package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.vojta.agilusesmockcontroller.common.Constants
import me.vojta.agilusesmockcontroller.domain.DomainFacade
import me.vojta.agilusesmockcontroller.domain.opc.Credentials

@Composable
fun Connect(domain: DomainFacade) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isConnected = remember { mutableStateOf(false) }
    val isError = remember { mutableStateOf(false) }

    val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        isError.value = true
    }


    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column(modifier = Modifier.width(500.dp)) {

            OutlinedTextField(value = username.value,
                onValueChange = {
                    username.value = it
                    isError.value = false
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isConnected.value,
                isError = isError.value)

            OutlinedTextField(value = password.value,
                onValueChange = {
                    password.value = it
                    isError.value = false
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isConnected.value,
                isError = isError.value)

            if (isError.value) {
                Text(
                    text = "Connection Error! Check credentials!",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {

            Button(
                onClick = {
                    GlobalScope.launch(exceptionHandler) {
                        if (isConnected.value) {
                            domain.disconnect()
                        } else {
                            domain.connect(Constants.ENDPOINT, Credentials(username.value, password.value))
                        }
                        isConnected.value = !isConnected.value
                    }
                },
                modifier = Modifier.padding(7.dp).requiredHeight(120.dp).requiredWidth(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = if (isConnected.value) Color.Red else Color.Green)
            ) {
                Text(if (isConnected.value) "Disconnect" else "Connect")
            }
        }
    }
}


