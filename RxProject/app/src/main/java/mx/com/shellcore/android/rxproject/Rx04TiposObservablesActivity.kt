package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class Rx04TiposObservablesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx04_tipos_observables)

        observableObserver()
    }

    private fun observableObserver() {
        "OBSERVABLE - OBSERVER".showTitle()
        val empleados = Empleado.getListaEmpleados()
        val observable = Observable.create<Empleado> {
            for (empleado in empleados) {
                it.onNext(empleado)
            }
            it.onComplete()
        }

        val observer = object : Observer<Empleado> {
            override fun onComplete() {
                "onComplete".showLog()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Empleado) {
                "onNext: ${t.nombre}".showLog()
            }

            override fun onError(e: Throwable) {
            }
        }

        observable.subscribe(observer)
    }
}

private fun String.showLog() {
    Log.d("Rx4Observables", this)
}

private fun String.showTitle() {
    "------------------------------ $this ------------------------------".showLog()
}
