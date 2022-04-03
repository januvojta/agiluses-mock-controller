package me.vojta.agilusesmockcontroller.domain

import me.vojta.agilusesmockcontroller.domain.opc.Credentials
import me.vojta.agilusesmockcontroller.domain.opc.EndpointUrl
import me.vojta.agilusesmockcontroller.domain.opc.MiloClientFacade

class Executor {
    private val client = MiloClientFacade()

    fun connect(endpoint: EndpointUrl, credentials: Credentials) {
        client.connect(endpoint, credentials)
    }

    fun disconnect(){
        client.disconnect()
    }

    suspend fun runOperation(operation: OpcRunnable){
        operation.run(client)
    }
}