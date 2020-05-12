package io.darkloned.springthebuilder

import io.darkloned.springthebuilder.disinfector.Disinfector
import io.darkloned.springthebuilder.infrastructure.context.Application
import io.darkloned.springthebuilder.policeman.Policeman
import io.darkloned.springthebuilder.policeman.impl.PolitePoliceman

fun main() {
    val context = Application.run(
        "io.darkloned.springthebuilder",
        mapOf(Policeman::class.java to PolitePoliceman::class.java)
    )
    val disinfector = context.getObject(Disinfector::class.java)

    println("LOG: Disinfector[${disinfector.javaClass.simpleName}] was created")
    disinfector.startDisinfection()
}
