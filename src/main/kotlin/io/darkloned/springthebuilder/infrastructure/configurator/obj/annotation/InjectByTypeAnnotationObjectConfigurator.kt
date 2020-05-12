package io.darkloned.springthebuilder.infrastructure.configurator.obj.annotation

import io.darkloned.springthebuilder.infrastructure.context.ApplicationContext
import io.darkloned.springthebuilder.infrastructure.annotation.InjectByType
import io.darkloned.springthebuilder.infrastructure.configurator.obj.ObjectConfigurator

class InjectByTypeAnnotationObjectConfigurator : ObjectConfigurator {

    override fun configure(obj: Any, context: ApplicationContext) {
        obj.javaClass
            .declaredFields
            .filter { it.isAnnotationPresent(InjectByType::class.java) }
            .forEach {
                it.isAccessible = true
                it.set(obj, context.getObject(it.type))
            }
    }
}
