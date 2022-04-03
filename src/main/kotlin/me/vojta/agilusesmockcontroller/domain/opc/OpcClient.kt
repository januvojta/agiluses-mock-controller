package me.vojta.agilusesmockcontroller.domain.opc

interface OpcClient {
    fun disconnect()
    fun isConnected(): Boolean
    fun read(namespaceIndex: Int, path: String): Any
    fun write(namespaceIndex: Int, path: String, data: Any)
    fun browse(namespaceIndex: Int, path: String): List<String>
    fun connect(endpoint: EndpointUrl, credentials: Credentials)
}