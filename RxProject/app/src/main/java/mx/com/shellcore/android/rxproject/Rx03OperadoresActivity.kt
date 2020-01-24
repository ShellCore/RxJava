package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Rx03OperadoresActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Rx03Operadores"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx03_operadores)

//        probarJust()
        probarJustArray()
    }

    private fun probarJust() {
        showLog("-------------------JUST---------------------")
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(number: String) {
                    showLog("Just -> onNext($number)")
                }

                override fun onError(e: Throwable) {

                }
            })
    }

    private fun probarJustArray() {
        showLog("----------------JUST ARRAY------------------")
        val numbers = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        Observable.just(numbers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Array<String>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Array<String>) {
                    showLog("JustArray -> onNext (${t.size})")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun showLog(message: String) {
        Log.d(TAG, message)
    }
}
