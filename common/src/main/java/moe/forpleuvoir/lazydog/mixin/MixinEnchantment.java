package moe.forpleuvoir.lazydog.mixin;

import moe.forpleuvoir.lazydog.feature.vanilla_modify.infinity_bucket.InfinityEnchantmentModifier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class MixinEnchantment {

    @SuppressWarnings("all")
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    public void lazyDog$isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this.equals(Enchantments.INFINITY)) {
            cir.setReturnValue(InfinityEnchantmentModifier.isInfinityBucket(stack.getItem()));
        }
    }

}
