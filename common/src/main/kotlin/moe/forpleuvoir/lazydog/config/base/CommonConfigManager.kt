package moe.forpleuvoir.lazydog.config.base

import moe.forpleuvoir.lazydog.LazyDog
import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import java.io.File
import java.nio.file.Path

open class CommonConfigManager(modID: String, key: String) : ModConfigManager<LazyDog>(modID, key) {

    override val configPath: Path
        get() = File(LazyDogExpectPlatform.getConfigDirectory().toFile(), modID).toPath()
}