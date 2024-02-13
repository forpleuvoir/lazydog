package moe.forpleuvoir.lazydog.feature.vanilla_modify.infinity_bucket

import moe.forpleuvoir.lazydog.config.CommonConfig.VanillaModify.infinityBucketList
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries

object InfinityEnchantmentModifier {

    @JvmStatic
    fun isInfinityBucket(item: Item): Boolean {
        return infinityBucketList.contains(Registries.ITEM.getKey(item).get().value.toString())
    }

    @JvmStatic
    fun isInfinityBucket(stack: ItemStack): Boolean {
        return EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0
    }

    @JvmStatic
    fun getEmptiedStack(stack: ItemStack, player: PlayerEntity): ItemStack {
        val hasInfinityEnchantment = EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0
        return if (player.abilities.creativeMode || hasInfinityEnchantment) stack else ItemStack(Items.BUCKET)
    }

}