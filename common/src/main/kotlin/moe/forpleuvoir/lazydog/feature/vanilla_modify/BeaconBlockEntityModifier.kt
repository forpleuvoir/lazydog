package moe.forpleuvoir.lazydog.feature.vanilla_modify

import moe.forpleuvoir.lazydog.effect.ModEffects
import net.minecraft.block.entity.BeaconBlockEntity
import net.minecraft.entity.effect.StatusEffects

object BeaconBlockEntityModifier : Modifier {
    override fun apply() {
        val effects = arrayOf(
            arrayOf(StatusEffects.SPEED, StatusEffects.HASTE),
            arrayOf(StatusEffects.RESISTANCE, StatusEffects.JUMP_BOOST),
            arrayOf(StatusEffects.STRENGTH),
            arrayOf(StatusEffects.REGENERATION, ModEffects.FLY),
        )

        BeaconBlockEntity.EFFECTS_BY_LEVEL = effects

        BeaconBlockEntity.EFFECTS = effects.flatMap { it.asIterable() }.toSet()
    }

}