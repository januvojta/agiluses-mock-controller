package me.vojta.agilusesmockcontroller.data

import me.vojta.agilusesmockcontroller.domain.OpcRunnable
import me.vojta.agilusesmockcontroller.domain.Operations

data class Robot(val name: String, val identificationNumber: Int, val stations: List<Station>) {
    val operations = Operations(this)
    var onActiveOperationChange: (value: String?) -> Unit = {}
    var activeOperation: OpcRunnable? = null
        set(value) {
            field = value
            onActiveOperationChange(value?.getName())
        }
}
