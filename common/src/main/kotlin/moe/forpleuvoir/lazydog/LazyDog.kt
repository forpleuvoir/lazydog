package moe.forpleuvoir.lazydog

import moe.forpleuvoir.lazydog.config.ClientConfig

open class LazyDog {

    val id: String = "lazydog"

    val name:String  = "Lazy Dog"

    fun init(){
        ClientConfig.clientInit()


    }



}