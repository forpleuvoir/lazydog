package moe.forpleuvoir.lazydog.mixin;

import moe.forpleuvoir.lazydog.feature.vanilla_modify.infinity_bucket.InfinityEnchantmentModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class MixinBucketItem {

    @Inject(method = "getEmptiedStack", at = @At("HEAD"), cancellable = true)
    private static void lazyDog$getEmptiedStack(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(InfinityEnchantmentModifier.getEmptiedStack(stack, player));
    }

}
