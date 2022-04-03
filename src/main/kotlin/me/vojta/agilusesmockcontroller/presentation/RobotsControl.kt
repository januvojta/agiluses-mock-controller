package me.vojta.agilusesmockcontroller.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.vojta.agilusesmockcontroller.common.Constants
import me.vojta.agilusesmockcontroller.domain.DomainFacade

@Composable
fun RobotsControl(domain: DomainFacade) {

    Column(modifier = Modifier.width(634.dp)) {
        for (robot in domain.robots){
            RobotControl(robot)
        }
    }
}