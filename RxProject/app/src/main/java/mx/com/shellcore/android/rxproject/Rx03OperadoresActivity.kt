package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class Rx03OperadoresActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Rx03Operadores"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx03_operadores)

//        probarJust()
//        probarJustArray()
//        probarFromArray()
//        probarRange()
//        probarRepeat()
//        probarCreate()
        probarInterval()
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

    private fun probarFromArray() {
        showLog("----------------FROM ARRAY------------------")
        val numbers =
            arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        Observable.fromArray(*numbers) // Así se marca para que Kotlin reconozca la variable numbers como String[], y pueda usarse fromArray
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onNext(t: String) {
                    showLog("FromArray -> onNext(${t})")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun probarRange() {
        showLog("----------------RANGE------------------")

        Observable.range(7, 17)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(integer: Int) {
                    showLog("Range -> $integer")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun probarRepeat() {
        showLog("----------------REPEAT------------------")

        Observable.range(10, 3)
            .repeat(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(integer: Int) {
                    showLog("Repeat -> $integer")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun probarCreate() {
        showLog("----------------CREATE------------------")

        Observable.create(ObservableOnSubscribe<String> {
            try {
                showLog("Subscribe. Hilo ${Thread.currentThread().name}")
                it.onNext("A")
                it.onNext("E")
                it.onNext("I")
                it.onNext("O")
                it.onNext("U")
            } catch (e: Exception) {
                    it.onError(e)
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(s: String) {
                    showLog("onNext: $s. Hilo: ${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun probarInterval() {
        showLog("----------------INTERVAL------------------")

        Observable.interval(1, TimeUnit.SECONDS)
            .take(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(s: Long) {
                    showLog("Interval: $s")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun showLog(message: String) {
        Log.d(TAG, message)
    }
}
