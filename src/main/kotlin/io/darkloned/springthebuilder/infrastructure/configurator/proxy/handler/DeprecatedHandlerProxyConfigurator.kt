package io.darkloned.springthebuilder.infrastructure.configurator.proxy.handler

import io.darkloned.springthebuilder.infrastructure.configurator.proxy.ProxyConfigurator
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class DeprecatedHandlerProxyConfigurator : ProxyConfigurator {

    override fun replaceWithProxyIfNeeded(obj: Any, implClass: Class<*>): Any {
        implClass.declaredMethods
            .mapNotNull { it.getAnnotation(Deprecated::class.java) }
            .takeIf { it.isNotEmpty() }
            ?.let {
                /*if (implClass.interfaces.isEmpty()) {
                    return Enhancer.create(implClass, InvocationHandler { _, method, args ->
                        runInvocationHandler(method, args, obj)
                    })
                }*/
                check(implClass.interfaces.isNotEmpty()) {
                    "${implClass.simpleName} doesn't implement any interfaces, unable to proxy"
                }

                return Proxy.newProxyInstance(implClass.classLoader, implClass.interfaces) { _, method, args ->
                    runInvocationHandler(method, args, obj, implClass)
                }
            }

        return obj
    }

    private fun runInvocationHandler(method: Method, args: Array<Any>?, obj: Any, implClass: Class<*>) {
        val methodName = method.name
        val reflectArgs = args ?: emptyArray()
        val langArgs = reflectArgs
            .map { it.javaClass }
            .toTypedArray()

        implClass
            .getDeclaredMethod(methodName, *langArgs)
            .getAnnotation(Deprecated::class.java)
            ?.let {
                println("=== ${methodName}() is deprecated : ${it.message} ===")
            }

        method.invoke(obj, *reflectArgs)
    }
}
