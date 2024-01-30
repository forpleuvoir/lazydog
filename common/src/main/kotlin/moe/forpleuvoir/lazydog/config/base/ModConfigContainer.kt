package moe.forpleuvoir.lazydog.config.base

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import moe.forpleuvoir.nebula.config.ConfigSerializable
import moe.forpleuvoir.nebula.config.container.ConfigContainerImpl
import moe.forpleuvoir.nebula.serialization.DeserializationException
import moe.forpleuvoir.nebula.serialization.base.SerializeElement
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

open class ModConfigContainer(key: String) : ConfigContainerImpl(key) {

    val log: Logger = LogManager.getLogger(LazyDogExpectPlatform.getLazyDog().id)

    override fun deserializationExceptionHandler(configSerializable: ConfigSerializable, serializeElement: SerializeElement, e: DeserializationException) {
        needSave = true
        log.error("${configSerializable.key}:${serializeElement} deserialization failed", e)
    }
}