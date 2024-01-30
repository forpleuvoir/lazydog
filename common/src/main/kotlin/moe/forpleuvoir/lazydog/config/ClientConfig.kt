package moe.forpleuvoir.lazydog.config

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import moe.forpleuvoir.lazydog.config.base.ClientModConfigManager
import moe.forpleuvoir.lazydog.config.base.ModConfigContainer
import moe.forpleuvoir.nebula.config.Description
import moe.forpleuvoir.nebula.config.item.impl.ConfigBoolean

object ClientConfig : ClientModConfigManager(LazyDogExpectPlatform.getLazyDog().id, "client_config") {

    @Description("渲染相关的配置")
    object Render : ModConfigContainer("render") {

        @Description("掉落物品相关的配置")
        object ItemEntity : ModConfigContainer("item_entity") {

            @Description("是否渲染掉落物品的附魔")
            var enchantment by ConfigBoolean("enchantment", true)

            @Description("是否渲染掉落物品的名称")
            var name by ConfigBoolean("name", true)

            @Description("是否渲染掉落物品的数量")
            var count by ConfigBoolean("count", true)
        }

    }


}