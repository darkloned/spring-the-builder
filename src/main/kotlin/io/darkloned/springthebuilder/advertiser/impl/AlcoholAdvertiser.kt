package io.darkloned.springthebuilder.advertiser.impl

import io.darkloned.springthebuilder.advertiser.Advertiser
import io.darkloned.springthebuilder.infrastructure.annotation.InjectProperty
import io.darkloned.springthebuilder.infrastructure.annotation.Singleton

@Singleton
class AlcoholAdvertiser : Advertiser {

    @InjectProperty("whisky")
    private lateinit var alcohol: String

    init {
        println("LOG: AlcoholAdvertiser was created")
    }

    override fun advertise() {
        println("Ad: \"In $alcohol veritas, in aqua sanitas\"")
    }
}
