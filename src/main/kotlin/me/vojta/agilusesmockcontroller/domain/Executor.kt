package me.vojta.agilusesmockcontroller.domain

import me.vojta.agilusesmockcontroller.domain.opc.EndpointUrl
import me.vojta.agilusesmockcontroller.domain.opc.MiloClientFacade

class Executor {
    private val client = MiloClientFacade()

    fun connect(endpoint: EndpointUrl) {
        client.connect(endpoint)
    }

    fun disconnect(){
        client.disconnect()
    }

    fun isConnected(): Boolean{
        return client.isConnected()
    }

    suspend fun runOperation(operation: OpcRunnable){
        operation.run(client)
    }
}