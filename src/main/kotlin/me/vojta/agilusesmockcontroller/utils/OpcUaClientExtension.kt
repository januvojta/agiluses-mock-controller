package me.vojta.agilusesmockcontroller.utils

import org.eclipse.milo.opcua.sdk.client.OpcUaClient
import org.eclipse.milo.opcua.stack.core.Identifiers
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePath
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePathElement
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse
import java.util.concurrent.CompletableFuture


fun OpcUaClient.translateBrowsePath(
    startingNode: NodeId,
    path: String,
    namespaceIndex: Int,
): CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> {
    return this.translateBrowsePaths(listOf(BrowsePath(startingNode, stringToRelativePath(namespaceIndex, path))))
}

fun OpcUaClient.translateBrowsePaths(
    startingNode: NodeId,
    vararg paths: String,
    namespaceIndex: Int,
): CompletableFuture<TranslateBrowsePathsToNodeIdsResponse> {
    return this.translateBrowsePaths(paths.map { path ->
        BrowsePath(startingNode, stringToRelativePath(namespaceIndex, path))
    })
}

fun stringToRelativePath(namespaceIndex: Int, path: String): RelativePath {
    val listOfRelativePathElements = path
        .filter { !it.isWhitespace() }
        .split("/")
        .map { pathElement: String ->
            RelativePathElement(
                Identifiers.HierarchicalReferences,
                false,
                true,
                QualifiedName(namespaceIndex, pathElement))
        }

    return RelativePath(listOfRelativePathElements.toTypedArray())
}
