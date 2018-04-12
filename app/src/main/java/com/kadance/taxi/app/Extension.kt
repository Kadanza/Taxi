package com.kadance.taxi.app

import com.kadance.taxi.kit.LogWrapper

/**
 * Created by Kenza on 12.04.2018.
 */



 fun Any.d(msg : String, e: Exception? = null) {
    return LogWrapper.debug(msg, e )
 }

fun Any.e(msg : String, e: Exception? = null) {
    return LogWrapper.error(msg, e )
}

fun Any.w(msg : String, e: Exception? = null) {
    return LogWrapper.warring(msg, e )
}

fun Any.i(msg : String, e: Exception? = null) {
    return LogWrapper.info(msg, e )
}






