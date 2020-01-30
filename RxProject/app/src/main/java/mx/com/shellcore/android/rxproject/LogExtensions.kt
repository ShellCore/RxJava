package mx.com.shellcore.android.rxproject

import android.util.Log

fun String.showLog() {
    Log.d("RxProject", this)
}

fun String.showTitle() {
    "------------------------------ $this ------------------------------".showLog()
}