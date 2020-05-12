package io.darkloned.springthebuilder.infrastructure.configurator.obj.annotation

import io.darkloned.springthebuilder.infrastructure.annotation.InjectProperty
import io.darkloned.springthebuilder.infrastructure.configurator.obj.ObjectConfigurator
import io.darkloned.springthebuilder.infrastructure.context.ApplicationContext
import java.io.BufferedReader
import java.io.FileReader
import java.lang.IllegalStateException
import java.util.stream.Collectors

class InjectPropertyAnnotationObjectConfigurator : ObjectConfigurator {

    private val properties: Map<String, String>

    init {
        val path = ClassLoader
            .getSystemClassLoader()
            .getResource("application.properties")
            ?.path
            ?: throw IllegalStateException("Invalid properties filepath")

        properties = BufferedReader(FileReader(path))
            .lines()
            .map { it.split("=") }
            .collect(Collectors.toMap({ it[0] }, { it[1] }))
    }

    override fun configure(obj: Any, context: ApplicationContext) {
        obj.javaClass
            .declaredFields
            .forEach { field ->
                field.getAnnotation(InjectProperty::class.java)?.let {
                    val name = it.name.ifEmpty { field.name }
                    field.isAccessible = true
                    field.set(obj, properties[name])
                }
            }
    }
}
