package moe.forpleuvoir.lazydog.config

import kotlinx.coroutines.runBlocking
import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import moe.forpleuvoir.lazydog.config.base.ClientModConfigManager
import moe.forpleuvoir.lazydog.config.base.ModConfigCategory
import moe.forpleuvoir.nebula.config.item.impl.ConfigBoolean
import moe.forpleuvoir.nebula.config.persistence.ConfigManagerPersistence
import moe.forpleuvoir.nebula.config.persistence.JsonConfigManagerPersistence

object ClientConfig : ClientModConfigManager(LazyDogExpectPlatform.getLazyDog().id, "client_config"), ConfigManagerPersistence by JsonConfigManagerPersistence {

    fun clientInit() {
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

    object Render : ModConfigCategory("render") {

        object ItemEntity : ModConfigCategory("render") {

            var enchantment by ConfigBoolean("enchantment", true)

            var name by ConfigBoolean("name", true)

            var count by ConfigBoolean("count", true)
        }

    }


}