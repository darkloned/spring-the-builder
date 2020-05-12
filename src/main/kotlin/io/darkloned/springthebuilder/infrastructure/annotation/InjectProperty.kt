package io.darkloned.springthebuilder.infrastructure.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectProperty(val name: String = "")
