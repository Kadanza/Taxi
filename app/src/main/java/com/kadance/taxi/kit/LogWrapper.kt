package com.kadance.taxi.kit

import android.text.TextUtils
import com.kadance.taxi.app.TaxiApp

/**
 * Created by Kenza on 12.04.2018.
 */

object  LogWrapper {

    val logKit = TaxiApp.appComponent?.getLogKit()

    fun debug( msg : String, exception: Exception?){
        logKit?.d(location, msg, exception)
    }

    fun error( msg : String, exception: Exception?){
        logKit?.e(location, msg, exception)
    }

    fun info( msg : String, exception: Exception?){
        logKit?.i(location, msg, exception)
    }

    fun warring( msg : String, exception: Exception?){
        logKit?.w(location, msg, exception)
    }



    /**
     *  Get a specific place in the code
     */

    internal val location: String
        get() {
            val className = LogWrapper::class.java.name
            val traces = Thread.currentThread().stackTrace
            var found = false

            for (i in traces.indices) {
                var trace = traces[i]

                try {
                    if (found) {
                         trace = traces[i + 3]

                        if (!trace.className.startsWith(className)) {
                            val clazz = Class.forName(trace.className)
                            return "[" + getClassName(clazz) + ":" + trace.methodName + ":" + trace.lineNumber + "]: "
                        }
                    } else if (trace.className.startsWith(className)) {
                        found = true
                        continue
                    }
                } catch (e: ClassNotFoundException) {
                }

            }

            return "[]: "
        }


    internal fun getClassName(clazz: Class<*>?): String {
        return if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.simpleName)) {
                clazz.simpleName
            } else getClassName(clazz.enclosingClass)

        } else ""

    }

}