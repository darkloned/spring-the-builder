package io.darkloned.springthebuilder.infrastructure.context

import io.darkloned.springthebuilder.infrastructure.configuration.impl.JavaConfiguration

class Application {

    companion object {
        fun run(packageToScan: String, ifc2ImplClass: Map<Class<*>, Class<*>>): ApplicationContext {
            val config = JavaConfiguration(packageToScan, ifc2ImplClass)
            return ApplicationContext(config).apply {
                factory = ObjectFactory(this)
                initCache()
            }
        }
    }
}
