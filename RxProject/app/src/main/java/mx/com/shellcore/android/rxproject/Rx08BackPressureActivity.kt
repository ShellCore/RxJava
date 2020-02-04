package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class Rx08BackPressureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx08_back_pressure)

//        generarBackPressure()
        backPressureBuffer()
    }

    private fun generarBackPressure() {
        val source = PublishSubject.create<Int>()
        val disposable = source.observeOn(Schedulers.io())
            .subscribe({ largeDurationOperation(it) },
                { "onError: ${it.localizedMessage}".showLog() },
                { "onComplete".showLog() })

        for (i in 0..10) {
            source.onNext(i)
            "Creando item observable $i".showLog()
        }
        source.onComplete()
    }

    private fun backPressureBuffer() {
        val source = Flowable.interval(1, TimeUnit.MILLISECONDS)
        val disposable = source.onBackpressureBuffer(1000)
            .observeOn(Schedulers.computation())
            .subscribe({
                "Consumiendo observables $it".showLog()
                Thread.sleep(100)
            }, {
                "onError: ${it.localizedMessage}".showLog()
            })
    }

    private fun largeDurationOperation(number: Int) {
        Thread.sleep(1000)
        "Consumiendo observable: $number".showLog()
    }
}
