package moe.forpleuvoir.lazydog.config

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import moe.forpleuvoir.lazydog.config.base.CommonConfigManager
import moe.forpleuvoir.lazydog.config.base.ModConfigContainer
import moe.forpleuvoir.nebula.config.Description
import moe.forpleuvoir.nebula.config.item.impl.ConfigBoolean

object CommonConfig : CommonConfigManager(LazyDogExpectPlatform.getLazyDog().id, "common_config") {

    @Description("原版修改相关的配置")
    object VanillaModify : ModConfigContainer("vanilla_modify") {

        @Description("是否添加信标效果")
        val beaconEffectAddition by ConfigBoolean("beacon_effect_addition", true)

    }


}