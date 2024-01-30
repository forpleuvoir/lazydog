package moe.forpleuvoir.lazydog.forge

import moe.forpleuvoir.lazydog.LazyDog
import moe.forpleuvoir.lazydog.effect.ModEffects
import net.minecraft.client.MinecraftClient
import net.minecraft.registry.RegistryKeys
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.RegisterEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod("lazydog")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object LazyDogForge : LazyDog() {

    val log: Logger = LogManager.getLogger(id)

    init {
        runForDist(
            { MOD_BUS.addListener(this::onClientSetup) },
            { MOD_BUS.addListener(::onServerSetup) }
        )
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        log.log(Level.INFO, "Hello! This is working!")
        super.init()
    }

    @SubscribeEvent
    fun onRegistry(event: RegisterEvent) {
        event.register(RegistryKeys.STATUS_EFFECT) { r ->
            ModEffects.init {
                r.register(it.id, it.effect)
            }
        }

    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        log.log(Level.INFO, "Initializing client...")
        super.clientInit(MinecraftClient.getInstance())
    }


    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        log.log(Level.INFO, "Server starting...")
    }

}