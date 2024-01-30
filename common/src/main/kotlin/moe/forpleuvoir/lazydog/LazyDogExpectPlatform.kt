package moe.forpleuvoir.lazydog

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.util.Identifier
import java.nio.file.Path

object LazyDogExpectPlatform {

    @JvmStatic
    @ExpectPlatform
    fun getLazyDog(): LazyDog {
        throw AssertionError()
    }

    @JvmStatic
    @ExpectPlatform
    fun getConfigDirectory(): Path {
        throw AssertionError()
    }

    @JvmStatic
    @ExpectPlatform
    fun getGameDirectory(): Path {
        throw AssertionError()
    }

}