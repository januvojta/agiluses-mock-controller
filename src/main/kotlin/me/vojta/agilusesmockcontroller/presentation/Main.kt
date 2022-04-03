package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import me.vojta.agilusesmockcontroller.presentation.ui.theme.AgilusControlAppTheme
import me.vojta.agilusesmockcontroller.presentation.ui.theme.Connect

fun main() = Window {
    AgilusControlAppTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()){
            Column() {
                Connect()
                RobotsControl()
            }
        }
    }
}