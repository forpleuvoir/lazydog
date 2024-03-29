package moe.forpleuvoir.lazydog.config.base

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import net.minecraft.client.MinecraftClient
import java.io.File
import java.nio.file.Path

open class ClientModConfigManager(modID: String, key: String) : ModConfigManager<MinecraftClient>(modID, key) {

    override val configPath: Path
        get() = File(LazyDogExpectPlatform.getConfigDirectory().toFile(), modID).toPath()


}