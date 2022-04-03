package me.vojta.agilusesmockcontroller.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.vojta.agilusesmockcontroller.common.Constants.ROBOT_01
import me.vojta.agilusesmockcontroller.common.Constants.ROBOT_02
import me.vojta.agilusesmockcontroller.common.Constants.ROBOT_03
import me.vojta.agilusesmockcontroller.data.Robot
import me.vojta.agilusesmockcontroller.domain.opc.Credentials
import me.vojta.agilusesmockcontroller.domain.opc.EndpointUrl

class DomainFacade {
    val robots = listOf<Robot>(ROBOT_01, ROBOT_02, ROBOT_03)
    private val executor = Executor()

    fun connect(endpoint: EndpointUrl, credentials: Credentials) {
        GlobalScope.launch(Dispatchers.IO) {
            executor.connect(endpoint, credentials)
        }
    }

    fun disconnect() {
        GlobalScope.launch(Dispatchers.IO) {
            executor.disconnect()
        }
    }

    fun runRandom(robot: Robot) {
        GlobalScope.launch {

            while (robot.activeOperation != null) {
                val result = async(Dispatchers.IO) {
                    robot.activeOperation = robot.operations.getRandomOperation()
                    executor.runOperation(robot.activeOperation!!)
                }
                result.await()
            }
        }
    }

    fun stopRandom(robot: Robot) {
        robot.activeOperation = null
    }
}