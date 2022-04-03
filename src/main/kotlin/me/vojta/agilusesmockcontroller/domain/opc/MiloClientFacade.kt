package me.vojta.agilusesmockcontroller.domain.opc

import me.vojta.agilusesmockcontroller.utils.translateBrowsePath
import org.eclipse.milo.opcua.sdk.client.OpcUaClient
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider
import org.eclipse.milo.opcua.stack.core.Identifiers
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription
import java.util.concurrent.atomic.AtomicBoolean

class MiloClientFacade : OpcClient {
    private lateinit var client: OpcUaClient;
    private var isConnected = AtomicBoolean(false)


    override fun isConnected(): Boolean {
        return this.isConnected.get()
    }


    override fun connect(endpoint: EndpointUrl) {
        // TODO: Get safety requirements as an argument
        if (isConnected.get()) return;

        client = OpcUaClient.create(endpoint.getEndpointUrl(),
            { endpoints -> endpoints.stream().findFirst() },
            { configBuilder ->
                configBuilder
                    .setApplicationName(LocalizedText.english("random-opc-operation-client"))
                    .setApplicationUri("urn:cz:cvut:ciirc:client")
                    .setRequestTimeout(Unsigned.uint(5000))
                    .setIdentityProvider(UsernameProvider("admin", "Montrac2020"))
                    .build()
            }
        )

        client.connect().get()
        isConnected.set(true)
    }

    override fun disconnect() {
        if (!isConnected.get()) return

        client.disconnect()
        isConnected.set(false)
    }

    override fun read(namespaceIndex: Int, path: String): Any {
        val nodeId = pathToNodeId(namespaceIndex, path)
        val variableNode = client.addressSpace.getVariableNode(nodeId)

        return variableNode.readValue().value.value
    }

    override fun write(namespaceIndex: Int, path: String, data: Any) {
        val nodeId = pathToNodeId(namespaceIndex, path)
        val dataValue = DataValue(Variant(data), null, null)
        val status = client.writeValue(nodeId, dataValue).get()

//        if (status.isBad) throw Exception("Error while writing data to node")
    }

    override fun browse(namespaceIndex: Int, path: String): List<String> {
        val nodeId = pathToNodeId(namespaceIndex, path)

        val browseDescription = BrowseDescription(
            nodeId,
            BrowseDirection.Forward,
            Identifiers.References,
            true,
            Unsigned.uint(NodeClass.Object.value or NodeClass.Variable.value),
            Unsigned.uint(BrowseResultMask.All.value)
        )

        val result = client.browse(browseDescription).get()
        return result.references.map { ref -> ref.browseName.name.toString() }
    }

    private fun pathToNodeId(namespaceIndex: Int, path: String): NodeId {
        val pathResult = client
            .translateBrowsePath(Identifiers.ObjectsFolder, path, namespaceIndex)
            .get()
            .results.first()

        return pathResult
            .targets.first()
            .targetId
            .toNodeId(client.namespaceTable)
            .get()
    }
}