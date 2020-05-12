package io.darkloned.springthebuilder.infrastructure.configurator.proxy.handler

import io.darkloned.springthebuilder.infrastructure.configurator.proxy.ProxyConfigurator
import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class DeprecatedHandlerProxyConfigurator : ProxyConfigurator {

    // TODO: add support for @Deprecated above methods
    override fun replaceWithProxyIfNeeded(obj: Any, implClass: Class<*>): Any {
        implClass.getAnnotation(Deprecated::class.java)?.let { annotation ->
            val message = annotation.message

            if (implClass.interfaces.isEmpty()) {
                return Enhancer.create(implClass, InvocationHandler { _, method, args ->
                    runInvocationHandler(method, args, obj, message)
                })
            }

            return Proxy.newProxyInstance(implClass.classLoader, implClass.interfaces) { _, method, args ->
                runInvocationHandler(method, args, obj, message)
            }
        }

        return obj
    }

    private fun runInvocationHandler(method: Method, args: Array<Any>?, obj: Any, message: String) {
        val safeArgs = args ?: emptyArray()

        println("=== deprecation alert: $message ===")
        method.invoke(obj, *safeArgs)
    }
}
