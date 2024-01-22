package moe.forpleuvoir.lazydog.fabric

import moe.forpleuvoir.lazydog.LazyDog
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path

object LazyDogExpectPlatformImpl {

    @JvmStatic
    fun getLazyDog(): LazyDog {
        return LazyDogFabric
    }

    @JvmStatic
    fun getConfigDirectory(): Path {
        return FabricLoader.getInstance().configDir
    }

    @JvmStatic
    fun getGameDirectory(): Path {
        return FabricLoader.getInstance().gameDir
    }

}