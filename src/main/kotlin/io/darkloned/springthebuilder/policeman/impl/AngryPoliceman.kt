package io.darkloned.springthebuilder.policeman.impl

import io.darkloned.springthebuilder.policeman.Policeman

class AngryPoliceman : Policeman {

    override fun makePeopleLeaveRoom() {
        println("*policeman angrily asks to leave*")
    }
}
