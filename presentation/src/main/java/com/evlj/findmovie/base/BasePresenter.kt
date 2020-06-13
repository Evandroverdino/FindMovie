package com.evlj.findmovie.base

import android.content.Intent
import android.support.annotation.CallSuper
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


abstract class BasePresenter<View>(viewClass: Class<View>) {

    private val viewNull: View
    private var viewReference: WeakReference<View>? = null

    val view: View?
        get() {
            var view = viewReference!!.get()
            if (view == null) {
                view = viewNull
                this.attachInternal(viewNull)
            }
            return view
        }

    val isAttached: Boolean
        get() = viewNull != viewReference!!.get()

    init {
        if (!viewClass.isInterface) {
            throw IllegalArgumentException(
                String.format(
                    "View (%s) declaration must be a interface.",
                    viewClass.simpleName
                )
            )
        }

        viewNull = Proxy.newProxyInstance(
            viewClass.classLoader,
            arrayOf<Class<*>>(viewClass),
            object : InvocationHandler {
                @Throws(Throwable::class)
                override operator fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {
                    if ("equals" == method.getName()) {
                        return method.invoke(this, args)
                    } else {
                        IllegalStateException(
                            String.format(
                                "There is no view (%s) attached to the run method: %s",
                                viewClass.simpleName,
                                method.getName()
                            )
                        ).printStackTrace()
                        return null
                    }
                }
            }) as View
        viewReference = WeakReference(viewNull)
    }

    @CallSuper
    fun attachView(view: View) {
        this.attachInternal(view)
    }

    fun start() {}

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {}

    fun stop() {}

    @CallSuper
    fun detachView() {
        this.attachInternal(viewNull)
    }

    private fun attachInternal(view: View) {
        viewReference!!.clear()
        viewReference = WeakReference(view)
    }

}