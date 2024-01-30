package moe.forpleuvoir.lazydog

import moe.forpleuvoir.lazydog.config.ClientConfig
import moe.forpleuvoir.lazydog.config.CommonConfig
import moe.forpleuvoir.lazydog.feature.vanilla_modify.BeaconBlockEntityModifier
import moe.forpleuvoir.nebula.common.ifc
import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer

open class LazyDog {

    val id: String = "lazydog"

    val name: String = "Lazy Dog"

    fun init() {
        CommonConfig.step(this)
        CommonConfig.VanillaModify.apply {
            beaconEffectAddition.ifc { BeaconBlockEntityModifier.apply() }
        }
    }


    fun clientInit(client: MinecraftClient) {
        ClientConfig.step(client)
    }

    fun serverInit(server: MinecraftServer) {

    }


}