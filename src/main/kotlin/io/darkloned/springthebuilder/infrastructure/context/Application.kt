package io.darkloned.springthebuilder.infrastructure.context

import io.darkloned.springthebuilder.infrastructure.configuration.impl.JavaConfiguration

class Application {

    // TODO: init all singletons which are not lazy
    companion object {
        fun run(packageToScan: String, ifc2ImplClass: Map<Class<*>, Class<*>>): ApplicationContext {
            val config = JavaConfiguration(packageToScan, ifc2ImplClass)
            return ApplicationContext(config).also {
                it.factory = ObjectFactory(it)
            }
        }
    }
}
