package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.*
import io.reactivex.disposables.Disposable

class Rx04ObservableTypesActivity : AppCompatActivity() {

    private val employees = Empleado.getListaEmpleados()
    private val employee = Empleado(1, "Carl Walter", "Mi Corporation", "25/04/2014", 2.66, 2.32)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx04_tipos_observables)

//        observableObserver()
//        singleSingleObserver()
//        maybeMaybeObserver()
        completableCompletableObserver()
    }

    private fun observableObserver() {
        "OBSERVABLE - OBSERVER".showTitle()
        val observable = Observable.create<Empleado> {
            for (employee in employees) {
                it.onNext(employee)
            }
            it.onComplete()
        }

        val observer = object : Observer<Empleado> {
            override fun onComplete() {
                "onComplete".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: Empleado) {
                "onNext: ${t.nombre}".showLog()
            }

            override fun onError(e: Throwable) {}
        }

        observable.subscribe(observer)
    }

    private fun singleSingleObserver() {
        "SINGLE - SINGLEOBSERVER".showTitle()

        val single = Single.create<Empleado> {
            it.onSuccess(employee)
        }

        val singleObserver = object : SingleObserver<Empleado> {
            override fun onSuccess(t: Empleado) {
                "onSuccess: ${t.nombre}".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {}
        }

        single.subscribe(singleObserver)
    }

    private fun maybeMaybeObserver() {
        "MAYBE - MAYBE_OBSERVER".showTitle()

        val maybe = Maybe.create<Empleado> {
            it.onSuccess(employee)
        }

        val maybeEmpty = Maybe.empty<Empleado>()

        val maybeObserver = object : MaybeObserver<Empleado> {
            override fun onSuccess(t: Empleado) {
                "onSuccess: ${t.nombre}".showLog()
            }

            override fun onComplete() {
                "onComplete".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {}
        }

        val maybeObserverEmpty = object : MaybeObserver<Empleado> {
            override fun onSuccess(t: Empleado) {}

            override fun onComplete() {
                "onCompleteEmpty".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {}

        }

        maybe.subscribe(maybeObserver)
        maybeEmpty.subscribe(maybeObserverEmpty)

    }

    private fun completableCompletableObserver() {
        "COMPLETABLE - COMPLETABLE_OBSERVER".showTitle()

        val completable = Completable.create {
            it.onComplete()
        }

        val completableObserver = object : CompletableObserver {
            override fun onComplete() {
                "onComplete".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {}
        }

        completable.subscribe(completableObserver)
    }
}
