package me.vojta.agilusesmockcontroller.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.vojta.agilusesmockcontroller.common.Constants.ROBOT_01
import me.vojta.agilusesmockcontroller.common.Constants.ROBOT_02
import me.vojta.agilusesmockcontroller.common.Constants.ROBOT_03
import me.vojta.agilusesmockcontroller.data.Robot
import me.vojta.agilusesmockcontroller.domain.opc.EndpointUrl

class DomainFacade {
    val robots = listOf<Robot>(ROBOT_01, ROBOT_02, ROBOT_03)
    private val executor = Executor()

    fun connect(endpoint: EndpointUrl) {
        executor.connect(endpoint)
    }

    fun disconnect() {
        executor.disconnect()
    }

    fun runRandom(robot: Robot) {
        GlobalScope.launch {
            robot.isActive.set(true)

            while (robot.isActive.get()) {
                val result = async(Dispatchers.IO) {
                    robot.activeOperation = robot.operations.getRandomOperation()
                    executor.runOperation(robot.activeOperation!!)
                }
                result.await()
            }

            robot.activeOperation = null
        }
    }

    fun stopRandom(robot: Robot) {
        robot.isActive.set(false)
    }
}