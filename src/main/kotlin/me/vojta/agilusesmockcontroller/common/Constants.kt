package me.vojta.agilusesmockcontroller.common

import me.vojta.agilusesmockcontroller.data.*
import me.vojta.agilusesmockcontroller.domain.opc.Endpoint

object Constants {
    val PICK_PROGRAM = Program("Pick", 3)
    val PLACE_PROGRAM = Program("Place", 4)
    val LEFT_STATION = Station("Left", 2, listOf(
        Element("Body", Position(0, 0, 5, 7)),
        Element("Cabin", Position(90, 0, 21, 7)),
        Element("Chassis", Position(0, 0, 5, 5)),
    ))
    val RIGHT_STATION = Station("Right", 3, listOf(
        Element("Body", Position(0, 0, 5, 7)),
        Element("Cabin", Position(90, 0, 21, 7)),
        Element("Chassis", Position(0, 0, 5, 5)),
    ))
    val TABLE_STATION = Station("Table",1, listOf(
        Element("Body", Position(0, 2, 6, 7)),
        Element("Cabin", Position(90, -9, 2, 9)),
        Element("Chassis", Position(0, 13, 6, 6)),
    ))

    val ROBOT_01 = Robot("Krc01",1, listOf(
        LEFT_STATION,
        RIGHT_STATION,
        TABLE_STATION
    ))
    val ROBOT_02 = Robot("Krc02", 2, listOf(
        LEFT_STATION,
        RIGHT_STATION,
        TABLE_STATION
    ))
    val ROBOT_03 = Robot("Krc03", 3, listOf(
        LEFT_STATION,
        TABLE_STATION
    ))
//    val ENDPOINT = Endpoint("opc.tcp://10.35.130.2:4840")
    val ENDPOINT = Endpoint("opc.tcp://localhost:12686")
}