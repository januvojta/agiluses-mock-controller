package me.vojta.agilusesmockcontroller.data

import me.vojta.agilusesmockcontroller.domain.OpcRunnable
import me.vojta.agilusesmockcontroller.domain.Operation
import me.vojta.agilusesmockcontroller.domain.Operations
import java.util.concurrent.atomic.AtomicBoolean

data class Robot(val name: String, val identificationNumber: Int, val stations: List<Station>) {
    var isActive = AtomicBoolean(false)
    var activeOperation: OpcRunnable? = null
    val operations = Operations(this)
}