package moe.forpleuvoir.lazydog.mixin.client;

import moe.forpleuvoir.lazydog.task.TickTaskScheduler;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "tick", at = @At("HEAD"))
    public void lazyDog$startTick(CallbackInfo ci) {
        TickTaskScheduler.Client.INSTANCE.startTick((MinecraftClient) (Object) this);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void lazyDog$endTick(CallbackInfo ci) {
        TickTaskScheduler.Client.INSTANCE.endTick((MinecraftClient) (Object) this);
    }

}
