package me.vojta.agilusesmockcontroller.domain

import me.vojta.agilusesmockcontroller.common.Constants
import me.vojta.agilusesmockcontroller.data.OperationData
import me.vojta.agilusesmockcontroller.data.Robot
import kotlin.random.Random

class Operations(private val robot: Robot) {
    val operations = mutableListOf<Operation>()

    init {
        robot.stations.forEach { station ->
            station.elements.forEach { element ->
                run {
                    operations.add(Operation(OperationData(element, station, Constants.PICK_PROGRAM, robot)))
                    operations.add(Operation(OperationData(element, station, Constants.PLACE_PROGRAM, robot)))
                }
            }
        }
    }

    fun getRandomOperation(): OpcRunnable {
        val operationIndex = Random.nextInt(0,operations.size - 1)
        return operations[operationIndex]
    }
}