package me.vojta.agilusesmockcontroller.domain

import kotlinx.coroutines.delay
import me.vojta.agilusesmockcontroller.data.OperationData
import me.vojta.agilusesmockcontroller.domain.opc.OpcClient
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte

class Operation (val operationData: OperationData): OpcRunnable{


    override suspend fun run(client: OpcClient){
        if (!client.isConnected()) return


        while (!(client.read(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericOutput/OperationFinished") as Boolean)) {
            delay(1000L)
        }

        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/ProgramNumber", ubyte(operationData.program.identificationNumber))
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/SourceFrame", ubyte(operationData.station.identificationNumber))
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/SourceRotationZ", operationData.element.position.rotZ)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/SourceX", operationData.element.position.x)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/SourceY", operationData.element.position.y)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/SourceZ", operationData.element.position.z)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/TargetFrame", ubyte(operationData.station.identificationNumber))
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/TargetRotationZ", operationData.element.position.rotZ)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/TargetX", operationData.element.position.x)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/TargetY", operationData.element.position.y)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/TargetZ", operationData.element.position.z)

        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/DataReady", true)

        while (!(client.read(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericOutput/OperationStarted") as Boolean)) {
            delay(1000L)
        }

        delay(100L)
        client.write(4, "opcRobot${operationData.robot.identificationNumber}/RobotGenericInput/DataReady", false)
    }

    override fun getName(): String {
        return "${operationData.program.name}-${operationData.element.name}-Station-${operationData.station.name}"
    }
}