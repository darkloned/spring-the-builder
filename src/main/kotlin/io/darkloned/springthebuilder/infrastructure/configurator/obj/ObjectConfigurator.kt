package io.darkloned.springthebuilder.infrastructure.configurator.obj

import io.darkloned.springthebuilder.infrastructure.context.ApplicationContext

interface ObjectConfigurator {

    fun configure(obj: Any, context: ApplicationContext)
}
