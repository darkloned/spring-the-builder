package io.darkloned.springthebuilder.disinfector.impl

import io.darkloned.springthebuilder.announcer.Announcer
import io.darkloned.springthebuilder.infrastructure.annotation.InjectByType
import io.darkloned.springthebuilder.disinfector.Disinfector
import io.darkloned.springthebuilder.policeman.Policeman

@Deprecated("RoomDisinfector is deprecated")
class RoomDisinfector : Disinfector {

    @InjectByType
    private lateinit var announcer: Announcer

    @InjectByType
    private lateinit var policeman: Policeman

    override fun startDisinfection() {
        announcer.announce("Disinfection is about to start...")
        policeman.makePeopleLeaveRoom()

        disinfectRoom()

        announcer.announce("Disinfection completed")
    }
    
    private fun disinfectRoom() {
        println("!!! Room disinfection in progress !!!")
    }
}
