package moe.forpleuvoir.lazydog.mixin;

import moe.forpleuvoir.lazydog.config.CommonConfig;
import net.minecraft.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BeaconBlockEntity.class)
abstract class MixinBeaconBlockEntity {

    @ModifyConstant(method = "applyPlayerEffects", constant = @Constant(intValue = 10,ordinal = 0))
    private static int lazyDog$applyPlayerEffects(int constant) {
        return CommonConfig.VanillaModify.INSTANCE.getBeaconLevelRange();
    }



}
