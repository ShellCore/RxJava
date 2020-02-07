package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class Rx02CompositeActivity : AppCompatActivity() {

    private lateinit var numeroObserver: DisposableObserver<String>
    private lateinit var numeroLetraObserver: DisposableObserver<String>

    private lateinit var numeroObservable: Observable<String>
    private lateinit var numeroLetraObservable: Observable<String>

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx02_composite)

        compositeDisposable = CompositeDisposable()

        numeroObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        numeroLetraObservable = Observable.just(
            "uno",
            "dos",
            "tres",
            "cuatro",
            "cinco",
            "seis"
        )

        numeroObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                Log.d("onComplete", "Completed")
            }

            override fun onNext(numero: String) {
                Log.d("onNext", "numero: $numero")
            }

            override fun onError(e: Throwable) {
                Log.d("onError", "Error: ${e.localizedMessage}")
            }
        }

        numeroLetraObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                Log.d("onComplete", "Completed")
            }

            override fun onNext(numero: String) {
                Log.d("onNext", "numero: $numero")
            }

            override fun onError(e: Throwable) {
                Log.d("onError", "Error: ${e.localizedMessage}")
            }
        }

        compositeDisposable.add(
            numeroObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(numeroObserver)
        )
        compositeDisposable.add(
            numeroLetraObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(numeroLetraObserver)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
