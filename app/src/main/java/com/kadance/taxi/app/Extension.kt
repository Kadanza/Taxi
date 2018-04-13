package com.kadance.taxi.app

import android.content.Context
import android.widget.Toast
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


fun Any.showToast(context: Context, msg : String) {
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}


fun Context.showToast( msg : String) {
    Toast.makeText(this ,msg,Toast.LENGTH_SHORT).show()
}







