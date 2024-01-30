package moe.forpleuvoir.lazydog.effect

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import moe.forpleuvoir.lazydog.task.TickTask
import moe.forpleuvoir.lazydog.task.TickTaskScheduler
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.AttributeContainer
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket
import net.minecraft.server.network.ServerPlayerEntity

class FlyEffect(color: Int) : StatusEffect(StatusEffectCategory.BENEFICIAL, color) {

    override fun onApplied(entity: LivingEntity, attributes: AttributeContainer, amplifier: Int) {
        if (entity is PlayerEntity) {
            entity.abilities.allowFlying = true
            if (entity is ServerPlayerEntity) {
                entity.networkHandler.sendPacket(PlayerAbilitiesS2CPacket(entity.getAbilities()))
            }
        }
    }


    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int) {
        if (entity is PlayerEntity) {
            entity.abilities.allowFlying = true
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun onRemoved(entity: LivingEntity, attributes: AttributeContainer?, amplifier: Int) {
        if (entity is PlayerEntity) {
            entity.abilities.allowFlying = false
            if (entity is ServerPlayerEntity) {
                entity.networkHandler.sendPacket(PlayerAbilitiesS2CPacket(entity.getAbilities()))
            }

            TickTaskScheduler.Server.scheduleStartTick(TickTask(5, 1, 1) {
                entity.statusEffects.find { it.effectType == this } ?: run {
                    entity.abilities.flying = false
                    if (entity is ServerPlayerEntity) {
                        entity.networkHandler.sendPacket(PlayerAbilitiesS2CPacket(entity.getAbilities()))
                    }
                }
            })
        }
    }

    override fun getTranslationKey(): String {
        return "effect.${LazyDogExpectPlatform.getLazyDog().id}.fly"
    }

}