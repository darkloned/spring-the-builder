package io.darkloned.springthebuilder.policeman.impl

import io.darkloned.springthebuilder.advertiser.Advertiser
import io.darkloned.springthebuilder.infrastructure.annotation.InjectByType
import io.darkloned.springthebuilder.policeman.Policeman
import javax.annotation.PostConstruct

class PolitePoliceman : Policeman {

    @InjectByType
    private lateinit var advertiser: Advertiser

    @PostConstruct
    fun init() {
        println("Second advertiser injection: ${advertiser.javaClass.simpleName}")
    }

    override fun makePeopleLeaveRoom() {
        println("*policeman politely asks to leave*")
    }
}
