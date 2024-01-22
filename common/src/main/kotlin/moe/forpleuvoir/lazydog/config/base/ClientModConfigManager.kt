package moe.forpleuvoir.lazydog.config.base

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import java.io.File
import java.nio.file.Path

abstract class ClientModConfigManager(modID: String, key: String) : ModConfigManager(modID, key) {
    override val configPath: Path
        get() = File(LazyDogExpectPlatform.getConfigDirectory().toFile(), modID).toPath()

}