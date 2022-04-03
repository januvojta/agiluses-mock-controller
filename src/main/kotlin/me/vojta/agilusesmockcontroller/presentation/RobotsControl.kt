package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RobotsControl() {
    Column(modifier = Modifier.width(634.dp)) {
        RobotControl()
        RobotControl()
    }
}