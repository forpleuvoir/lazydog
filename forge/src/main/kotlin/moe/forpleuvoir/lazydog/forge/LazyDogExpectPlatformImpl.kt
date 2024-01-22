package moe.forpleuvoir.lazydog.forge

import moe.forpleuvoir.lazydog.LazyDog
import net.minecraftforge.fml.loading.FMLPaths
import java.nio.file.Path

object LazyDogExpectPlatformImpl {

    @JvmStatic
    fun getLazyDog(): LazyDog {
        return LazyDogForge
    }

    @JvmStatic
    fun getConfigDirectory(): Path {
        return FMLPaths.CONFIGDIR.get()
    }

    @JvmStatic
    fun getGameDirectory(): Path {
        return FMLPaths.GAMEDIR.get()
    }
}