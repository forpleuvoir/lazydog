package moe.forpleuvoir.lazydog.mixin;

import moe.forpleuvoir.lazydog.feature.vanilla_modify.infinity_bucket.InfinityEnchantmentModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.spongepowered.asm.mixin.injection.callback.LocalCapture.CAPTURE_FAILHARD;

@Mixin(BucketItem.class)
public class MixinBucketItem {

    @Inject(method = "getEmptiedStack", at = @At("HEAD"), cancellable = true)
    private static void lazyDog$getEmptiedStack(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(InfinityEnchantmentModifier.getEmptiedStack(stack, player));
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    public ItemStack lazyDog$use(ItemStack inputStack, PlayerEntity player, ItemStack outputStack) {
        if (!InfinityEnchantmentModifier.isInfinityBucket(inputStack)) {
            return ItemUsage.exchangeStack(inputStack, player, outputStack);
        } else return inputStack;
    }

}
