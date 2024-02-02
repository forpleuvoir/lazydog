package moe.forpleuvoir.lazydog.config

import moe.forpleuvoir.lazydog.LazyDogExpectPlatform
import moe.forpleuvoir.lazydog.config.base.CommonConfigManager
import moe.forpleuvoir.lazydog.config.base.ModConfigContainer
import moe.forpleuvoir.nebula.config.Description
import moe.forpleuvoir.nebula.config.item.impl.ConfigBoolean
import moe.forpleuvoir.nebula.config.item.impl.ConfigInt
import moe.forpleuvoir.nebula.config.item.impl.ConfigStringList

object CommonConfig : CommonConfigManager(LazyDogExpectPlatform.getLazyDog().id, "common_config") {

    @Description("原版修改相关的配置")
    object VanillaModify : ModConfigContainer("vanilla_modify") {

        @Description("是否添加信标效果")
        val beaconEffectAddition by ConfigBoolean("beacon_effect_addition", true)

        @Description("信标等级范围增加,每级信标等级增加的范围，原版为10")
        val beaconLevelRange by ConfigInt("beacon_level_range", 20)

        @Description("无限附魔的桶")
        val infinityBucketList by ConfigStringList("infinity_bucket_list", listOf("minecraft:water_bucket"))

    }


}