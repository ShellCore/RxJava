package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class Rx09HotAndColdActivity : AppCompatActivity() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx09_hot_and_cold)

//        coldObservable()
        hotObservable()
    }

    private fun coldObservable() {
        val cold = Observable.interval(500, TimeUnit.MILLISECONDS)
        compositeDisposable.add(cold.subscribe {
            "Subscriber 1: $it".showLog()
        })
        Thread.sleep(2000)
        compositeDisposable.add(cold.subscribe {
            "Subscriber 2: $it".showLog()
        })
    }

    private fun hotObservable() {
        val hot = Observable.interval(500, TimeUnit.MILLISECONDS).publish()

        hot.connect()

        compositeDisposable.add(hot.subscribe {
            "Subscriber 1: $it".showLog()
        })
        Thread.sleep(2000)
        compositeDisposable.add(hot.subscribe {
            "Subscriber 2: $it".showLog()
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
