package moe.forpleuvoir.lazydog.fabric

import moe.forpleuvoir.lazydog.LazyDog
import moe.forpleuvoir.lazydog.effect.ModEffects
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object LazyDogFabric : LazyDog(), ModInitializer,ClientModInitializer {
    override fun onInitialize() {
        super.init()
        ModEffects.init {
            Registry.register(Registries.STATUS_EFFECT, it.id, it.effect)
        }
    }

    override fun onInitializeClient() {
        super.clientInit(MinecraftClient.getInstance())
    }


}