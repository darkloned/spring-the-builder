package io.darkloned.springthebuilder.infrastructure.configurator.proxy

interface ProxyConfigurator {

    fun replaceWithProxyIfNeeded(obj: Any, implClass: Class<*>): Any
}
