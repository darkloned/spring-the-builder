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
            val implClass = when {
                type.isInterface -> config.getImplClass(type)
                else -> type
            }
            val obj = factory.createObject(implClass)

            if (implClass.isAnnotationPresent(Singleton::class.java)) {
                cache[type] = obj
            }

            return obj
        } as T
}
