package io.darkloned.springthebuilder.infrastructure.configuration.impl

import io.darkloned.springthebuilder.infrastructure.configuration.Configuration
import org.reflections.Reflections

class JavaConfiguration(
    packageToScan: String,
    private val ifc2ImplClass: Map<Class<*>, Class<*>>
) : Configuration {

    val scanner = Reflections(packageToScan)

    @Suppress("UNCHECKED_CAST")
    override fun <T> getImplClass(ifc: Class<T>) =
        ifc2ImplClass.getOrElse(ifc) {
            val classes = scanner.getSubTypesOf(ifc)
            check(classes.size == 1) {
                "$ifc has 0 or more than one implementation"
            }

            return classes.iterator().next()
        } as Class<out T>
}
