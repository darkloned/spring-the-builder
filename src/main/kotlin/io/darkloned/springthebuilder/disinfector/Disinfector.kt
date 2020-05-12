package io.darkloned.springthebuilder.disinfector

import io.darkloned.springthebuilder.disinfector.place.Room

interface Disinfector {

    fun startDisinfection(vararg rooms: Room)
}
