package io.darkloned.springthebuilder.announcer.impl

import io.darkloned.springthebuilder.advertiser.Advertiser
import io.darkloned.springthebuilder.announcer.Announcer
import io.darkloned.springthebuilder.infrastructure.annotation.InjectByType

class ConsoleAnnouncer : Announcer {

    @InjectByType
    private lateinit var advertiser: Advertiser

    override fun announce(announcement: String) {
        println(announcement)
        advertiser.advertise()
    }
}
