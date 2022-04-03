package me.vojta.agilusesmockcontroller.domain.opc

interface OpcClient {
    fun connect(endpoint: EndpointUrl)
    fun disconnect()
    fun isConnected(): Boolean
    fun read(namespaceIndex: Int, path: String): Any
    fun write(namespaceIndex: Int, path: String, data: Any)
    fun browse(namespaceIndex: Int, path: String): List<String>
}