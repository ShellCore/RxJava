package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Rx00IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx00_intro)

        val numbersObservable: Observable<String> =
            Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val observer: Observer<String> = object : Observer<String> {
            override fun onComplete() {
                Log.d(
                    "onComplete",
                    "Hilo ${Thread.currentThread().name} : Se han emitido todos los datos"
                )
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("onSubscribe", "Hilo ${Thread.currentThread().name}")
            }

            override fun onNext(numero: String) {
                Log.d("onNext", "Hilo ${Thread.currentThread().name} : Número: $numero")
            }

            override fun onError(e: Throwable) {
                Log.d(
                    "onError",
                    "Hilo ${Thread.currentThread().name} : Error: ${e.localizedMessage}"
                )
            }
        }

        numbersObservable
            .subscribeOn(Schedulers.io()) // Donde queremos que se ejecute el observable
            .observeOn(AndroidSchedulers.mainThread()) // Donde queremos que se ejecute el observer (Hilo Main)
            .observeOn(Schedulers.io()) // Donde queremos que se ejecute el observer (Hilo RxCachedThreadScheduler-2)
            .subscribe(observer)
    }
}
