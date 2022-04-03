package me.vojta.agilusesmockcontroller.domain

import me.vojta.agilusesmockcontroller.domain.opc.OpcClient

interface OpcRunnable {

    suspend fun run(client: OpcClient)
    fun getName(): String
}