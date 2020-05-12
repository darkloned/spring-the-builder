package io.darkloned.springthebuilder.disinfector.impl

import io.darkloned.springthebuilder.announcer.Announcer
import io.darkloned.springthebuilder.disinfector.Disinfector
import io.darkloned.springthebuilder.disinfector.place.Room
import io.darkloned.springthebuilder.infrastructure.annotation.InjectByType
import io.darkloned.springthebuilder.policeman.Policeman

class RoomDisinfector : Disinfector {

    @InjectByType
    private lateinit var announcer: Announcer

    @InjectByType
    private lateinit var policeman: Policeman

    @Deprecated("implement a new method")
    override fun startDisinfection(vararg rooms: Room) {
        announcer.announce("Disinfection is about to start...")
        policeman.makePeopleLeaveRoom()

        disinfectRoom()

        announcer.announce("Disinfection completed")
    }
    
    private fun disinfectRoom() {
        println("!!! Room disinfection in progress !!!")
    }
}
