package moe.forpleuvoir.lazydog.config.base

import kotlinx.coroutines.runBlocking
import moe.forpleuvoir.nebula.config.ConfigSerializable
import moe.forpleuvoir.nebula.config.manager.ConfigManagerImpl
import moe.forpleuvoir.nebula.config.manager.plugin.localConfig
import moe.forpleuvoir.nebula.config.manager.plugins
import moe.forpleuvoir.nebula.config.persistence.jsonPersistence
import moe.forpleuvoir.nebula.serialization.DeserializationException
import moe.forpleuvoir.nebula.serialization.base.SerializeElement
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

abstract class ModConfigManager<T>(protected val modID: String, key: String) : ConfigManagerImpl(key) {

    val log: Logger = LogManager.getLogger(modID)

    abstract val configPath: Path

    init {
        plugins {
            localConfig(configPath, jsonPersistence())
        }
    }

    open fun step(context: T) {
        init()
        runBlocking {
            runCatching {
                load()
            }.onFailure {
                forceSave()
                log.error(it)
            }
        }
    }


    override fun deserializationExceptionHandler(configSerializable: ConfigSerializable, serializeElement: SerializeElement, e: DeserializationException) {
        needSave = true
        log.error("${configSerializable.key}:${serializeElement} deserialization failed", e)
    }
}