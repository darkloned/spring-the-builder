package io.darkloned.springthebuilder.infrastructure.context

import io.darkloned.springthebuilder.infrastructure.annotation.Singleton
import io.darkloned.springthebuilder.infrastructure.configuration.impl.JavaConfiguration
import java.util.concurrent.ConcurrentHashMap

class ApplicationContext(
    val config: JavaConfiguration
) {

    lateinit var factory: ObjectFactory

    private val cache = ConcurrentHashMap<Class<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> getObject(type: Class<T>): T =
        cache.getOrElse(type) {
            requestNewObject(type)
        } as T

    fun initCache() =
        config
            .scanner
            .getTypesAnnotatedWith(Singleton::class.java)
            .forEach { type ->
                val obj = requestNewObject(type)
                type.interfaces.forEach { ifc ->
                    cache[ifc] = obj
                }
            }

    private fun <T: Any> requestNewObject(type: Class<T>): T {
        val implClass = when {
            type.isInterface -> config.getImplClass(type)
            else -> type
        }
        return factory.createObject(implClass)
    }
}
