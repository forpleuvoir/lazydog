package moe.forpleuvoir.lazydog.config.base

import moe.forpleuvoir.nebula.config.ConfigSerializable
import moe.forpleuvoir.nebula.config.manager.LocalConfigManager
import moe.forpleuvoir.nebula.serialization.DeserializationException
import moe.forpleuvoir.nebula.serialization.base.SerializeElement
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

abstract class ModConfigManager(protected val modID: String, key: String) : LocalConfigManager(key) {

    val log: Logger = LogManager.getLogger(modID)

    override fun deserializationExceptionHandler(configSerializable: ConfigSerializable, serializeElement: SerializeElement, e: DeserializationException) {
        needSave = true
        log.error("${configSerializable.key}:${serializeElement} deserialization failed", e)
    }
}