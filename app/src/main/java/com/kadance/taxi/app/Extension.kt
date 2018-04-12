package com.kadance.taxi.app

import com.kadance.taxi.kit.MyLog

/**
 * Created by Kenza on 12.04.2018.
 */



 fun Any.d(msg : String, e: Exception? = null) {
    return MyLog.debug(msg, e )
 }

fun Any.e(msg : String, e: Exception? = null) {
    return MyLog.error(msg, e )
}

fun Any.w(msg : String, e: Exception? = null) {
    return MyLog.warring(msg, e )
}

fun Any.i(msg : String, e: Exception? = null) {
    return MyLog.info(msg, e )
}






