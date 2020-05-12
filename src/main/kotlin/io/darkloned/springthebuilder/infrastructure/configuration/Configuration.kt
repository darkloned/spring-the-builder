package io.darkloned.springthebuilder.infrastructure.configuration

interface Configuration {

    fun <T> getImplClass(ifc: Class<T>): Class<out T>
}
