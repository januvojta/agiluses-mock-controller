package me.vojta.agilusesmockcontroller.domain.opc

interface EndpointUrl {
    fun getEndpointUrl(): String {
        return "opc.tcp://localhost:12686/milo"
    }
}