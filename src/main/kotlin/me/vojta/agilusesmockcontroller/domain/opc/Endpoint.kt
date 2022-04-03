package me.vojta.agilusesmockcontroller.domain.opc

class Endpoint(private var url: String) : EndpointUrl {
    constructor(scheme:String = "opc.tcp", ip: String, port: Int) : this("${scheme}://${ip}:${port}") {
    }

    override fun getEndpointUrl(): String {
        return this.url
    }
}