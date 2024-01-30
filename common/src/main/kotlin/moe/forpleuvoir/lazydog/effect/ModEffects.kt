package moe.forpleuvoir.lazydog.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.util.Colors
import net.minecraft.util.Identifier

object ModEffects {
    private val effects = mutableListOf<Effect>()


    val FLY = register(FlyEffect(Colors.WHITE), Identifier("lazydog", "fly"))

    fun init(effect: (Effect) -> Unit) {
        effects.forEach {
            effect.invoke(it)
        }
    }

    private fun <T : StatusEffect> register(effect: T, identifier: Identifier): T {
        effects.add(Effect(effect, identifier))
        return effect
    }

    data class Effect(
        val effect: StatusEffect,
        val id: Identifier
    )

}