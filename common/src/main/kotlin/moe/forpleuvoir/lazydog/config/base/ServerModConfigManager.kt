package moe.forpleuvoir.lazydog.config.base

import kotlinx.coroutines.runBlocking
import net.minecraft.server.MinecraftServer
import java.io.File
import java.nio.file.Path

open class ServerModConfigManager(modID: String, key: String) : ModConfigManager<MinecraftServer>(modID, key) {

    protected open lateinit var server: MinecraftServer

    override fun step(context: MinecraftServer) {
        init(context)
        runBlocking {
            runCatching {
                load()
            }.onFailure {
                forceSave()
                log.error(it)
            }
        }
    }

    private fun init(server: MinecraftServer) {
        this.server = server
        init()
    }

    override val configPath: Path
        get() = File(server.session.directory.path.toFile(), modID).toPath()

}