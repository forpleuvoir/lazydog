package moe.forpleuvoir.lazydog.mixin;

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform;
import moe.forpleuvoir.lazydog.task.TickTaskScheduler;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(method = "tick", at = @At("HEAD"))
    public void lazyDog$startTick(CallbackInfo ci) {
        TickTaskScheduler.Server.INSTANCE.startTick((MinecraftServer) (Object) this);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void lazyDog$endTick(CallbackInfo ci) {
        TickTaskScheduler.Server.INSTANCE.endTick((MinecraftServer) (Object) this);
    }

    @Inject(method = "runServer", at = @At("HEAD"))
    public void lazyDog$runServer(CallbackInfo ci) {
        LazyDogExpectPlatform.getLazyDog().serverInit((MinecraftServer) (Object) this);
    }

}
