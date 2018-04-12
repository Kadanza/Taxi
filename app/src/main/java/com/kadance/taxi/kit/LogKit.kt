package com.kadance.taxi.kit

import android.util.Log

/**
 * Created by Kenza on 12.04.2018.
 */

class LogKit(){

    fun d(tag : String, msg : String, exception: Exception?){
        Log.d(tag, msg, exception)
    }

    fun i(tag : String, msg : String, exception: Exception?){
        Log.i(tag, msg, exception)
    }

    fun e(tag : String, msg : String, exception: Exception?){
        Log.e(tag, msg, exception)
    }

    fun w(tag: String, msg: String, exception: Exception?) {
        Log.w(tag, msg, exception)
    }
}