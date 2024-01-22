package moe.forpleuvoir.lazydog.feature.itementity

import moe.forpleuvoir.lazydog.config.ClientConfig.Render.ItemEntity.count
import moe.forpleuvoir.lazydog.config.ClientConfig.Render.ItemEntity.enchantment
import moe.forpleuvoir.lazydog.config.ClientConfig.Render.ItemEntity.name
import moe.forpleuvoir.lazydog.util.TextRenderUtil
import moe.forpleuvoir.lazydog.util.literal
import moe.forpleuvoir.lazydog.util.translatable
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import java.util.*


object ItemEntityRender {

    @JvmStatic
    fun render(
        itemEntity: ItemEntity,
        yaw: Float,
        tickDelta: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
        textRenderer: TextRenderer,
        dispatcher: EntityRenderDispatcher
    ) {
        renderTexts(itemEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, textRenderer, dispatcher)
    }

    fun renderTexts(
        itemEntity: ItemEntity,
        yaw: Float,
        tickDelta: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        light: Int,
        textRenderer: TextRenderer,
        dispatcher: EntityRenderDispatcher
    ) {
        val texts: LinkedList<Text> = LinkedList<Text>()
        renderEnchantmentText(texts, itemEntity)
        renderName(texts, itemEntity)
        if (!texts.isEmpty()) TextRenderUtil.renderEntityMultiText(itemEntity, texts, dispatcher, textRenderer, matrixStack, vertexConsumerProvider, light);
    }

    fun renderEnchantmentText(list: MutableList<Text>, itemEntity: ItemEntity) {
        if (enchantment) list.addAll(getEnchantmentsWithLvl(itemEntity.stack))
    }

    fun renderName(list: MutableList<Text>, itemEntity: ItemEntity) {
        val text = Text.empty()
        if (name) text.append(itemEntity.stack.name).formatted(itemEntity.stack.rarity.formatting)

        if (count && itemEntity.stack.count > 1) {
            text.append(String.format(" %d ", itemEntity.stack.count))
        }
        if (text.string.isNotEmpty()) list.add(text)
    }

    @JvmStatic
    fun getEnchantmentsWithLvl(stack: ItemStack, vararg formatting: Formatting?): List<Text> {
        val texts = LinkedList<Text>()
        EnchantmentHelper.get(stack).forEach { (enchantment, lvl) ->
            val text = translatable(enchantment.translationKey)
            if (lvl <= 10 && lvl != 1)
                text.append(translatable("enchantment.level.$lvl", null))
            else if (lvl > 10) text.append(literal("$lvl"))
            text.formatted(*formatting)
            texts.add(text)
        }
        return texts
    }

}