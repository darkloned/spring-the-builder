package io.darkloned.springthebuilder.infrastructure.context

import io.darkloned.springthebuilder.infrastructure.configurator.obj.ObjectConfigurator
import io.darkloned.springthebuilder.infrastructure.configurator.proxy.ProxyConfigurator
import javax.annotation.PostConstruct

class ObjectFactory(
    private val context: ApplicationContext
) {

    private val objectConfigurators: List<ObjectConfigurator> =
        context
            .config
            .scanner
            .getSubTypesOf(ObjectConfigurator::class.java).map {
                it.getDeclaredConstructor()
                    .newInstance()
            }

    private val proxyConfigurators: List<ProxyConfigurator> =
        context
            .config
            .scanner
            .getSubTypesOf(ProxyConfigurator::class.java)
            .map {
                it.getDeclaredConstructor()
                    .newInstance()
            }

    fun <T: Any> createObject(implClass: Class<T>): T {
        val obj = create(implClass).also {
            configure(it)
            invokeInit(implClass, it)
        }

        return replaceWithProxyIfNeeded(obj, implClass)
    }

    private fun <T: Any> create(implClass: Class<T>) =
        implClass.getDeclaredConstructor().newInstance()

    private fun <T: Any> configure(obj: T) =
        objectConfigurators.forEach {
            it.configure(obj, context)
        }

    private fun <T: Any> invokeInit(implClass: Class<T>, obj: Any) =
        implClass
            .methods
            .filter { it.isAnnotationPresent(PostConstruct::class.java) }
            .forEach { it.invoke(obj) }

    @Suppress("UNCHECKED_CAST")
    private fun <T: Any> replaceWithProxyIfNeeded(obj: T, implClass: Class<T>): T {
        var proxy = obj
        proxyConfigurators.forEach {
            proxy = it.replaceWithProxyIfNeeded(proxy, implClass) as T
        }

        return proxy
    }
}
