package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import me.vojta.agilusesmockcontroller.domain.DomainFacade
import me.vojta.agilusesmockcontroller.presentation.ui.theme.AgilusControlAppTheme

fun main() = Window(
    size = IntSize(700, 500),
    resizable = false,
    title = "Mock Controller for KUKA Agilus Robots"
) {
    AgilusControlAppTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()){
            Column() {
                val domain = DomainFacade()
                Connect(domain)
                RobotsControl(domain)
            }
        }
    }
}