package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.observables.GroupedObservable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Timed
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.activity_rx03_operadores.*
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class Rx03OperadoresActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Rx03Operadores"
    }

    private val numbers = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx03_operadores)

//        probarJust()
//        probarJustArray()
//        probarFromArray()
//        probarRange()
//        probarRepeat()
//        probarCreate()
//        probarInterval()
//        probarCreateException()
//        probarLargaDuracion()
//        probarLargaDuracionLamda()
//        probarbuffer()
//        probarMap()
//        probarFlatMap()
//        probarGroupBy()
//        probarScan()
//        probarWindow()
//        probarDebounce()
//        probarDistinct()
//        probarElementAt()
//        probarFilter()
//        probarFirst()
//        probarIgnoreElements()
//        probarLast()
//        probarSample()
//        probarSkip()
//        probarSkipLast()
//        probarTake()
//        probarTakeLast()
//        probarCombineLast()
//        probarJoin()
//        probarMerge()
//        probarZip()
//        probarRetry()
//        probarDelay()
//        probarDo()
//        probarObserveOnSubscribeOn()
//        probarTimeInterval()
//        probarTimeOut()
//        probarTimeStamp()
//        probarUsing()
//        probarAll()
//        probarAmb()
//        probarContains()
//        probarDefaultIfEmpty()
//        probarSequenceEqual()
//        probarSkipUntil()
        probarTakeUntil()
    }

    private fun probarJust() {
        showLog("-------------------JUST---------------------")
        numbers
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(number: Int) {
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

    private fun probarCreateException() {
        showLog("----------------CREATE EXCEPTION------------------")

        Observable.create<Int> {
            try {
                it.onNext(15 / 3)
                it.onNext(3 / 0)
            } catch (e: Exception) {
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    showLog("onNext: $t")
                }

                override fun onError(e: Throwable) {
                    showLog("onError: ${e.localizedMessage}")
                }

            })
    }

    private fun probarLargaDuracion() {
        showLog("----------------CREATE EXCEPTION------------------")
        Observable.create<String> {
            try {
                showLog("Subscribe. Hilo ${Thread.currentThread().name}")
                it.onNext(tareaLargaDuracion())
            } catch (e: Exception) {
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    showLog("Subscribe. Hilo ${Thread.currentThread().name}")
                    showLog("onNext: $t")
                }

                override fun onError(e: Throwable) {
                    showLog("OnError: ${e.localizedMessage}")
                }
            })
    }

    private fun probarLargaDuracionLamda() {
        showLog("----------------CREATE EXCEPTION------------------")
        val disposable = Observable.create<String> {
            try {
                showLog("Subscribe. Hilo ${Thread.currentThread().name}")
                it.onNext(tareaLargaDuracion())
            } catch (e: Exception) {
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ s ->
                showLog("Subscribe. Hilo ${Thread.currentThread().name}")
                showLog("onNext: $s")
            }, {
                showLog("OnError: ${it.localizedMessage}")
            })
    }

    private fun probarbuffer() {
        showLog("----------------BUFFER------------------")
        val disposable = numbers.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(3)
            .subscribe {
                showLog("Buffer onNext")
                it.iterator().forEach { item ->
                    showLog("Buffer item: $item")
                }
            }
    }

    private fun probarMap() {
        showLog("-------------MAP---------------")
        val empleados = Empleado.getListaEmpleados()

        val observable = Observable.fromArray(empleados)
            .map {
                val nombres = ArrayList<String>()
                for (empleado in it) {
                    nombres.add(empleado.nombre)
                }
                nombres
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showLog("MapItems: $it")
            }
    }

    private fun probarFlatMap() {
        showLog("---------------FLATMAP----------------")
        val observable = Observable.just("item2", "item3")
            .flatMap {
                showLog("Inside the FlatMap $it")
                Observable.just("$it 1", "$it 2", "$it 3")
            }
            .subscribe {
                showLog("Result: $it")
            }

    }

    private fun probarGroupBy() {
        showLog("----------------GROUPBY-------------------")
        val groupObservable: Observable<GroupedObservable<String, Int>> = numbers.groupBy {
            if (it % 2 == 0) "PAR" else "IMPAR"
        }
        val disposable = groupObservable.subscribe { stringIntGroupedObservable ->
            stringIntGroupedObservable.subscribe {
                if (stringIntGroupedObservable.key == "PAR") {
                    showLog("PAR: $it")
                } else {
                    showLog("IMPAR: $it")
                }
            }
        }
    }

    private fun probarScan() {
        showLog("-------------------SCAN------------------")
        val disposable = numbers
            .scan { t1: Int, t2: Int ->
                t1 + t2
            }
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarWindow() {
        showLog("--------------------------WINDOW----------------------")
        val observable = Observable.range(1, 150)
            .window(3)
            .subscribe { windowObservable ->
                showLog("Siguiente ventana")
                windowObservable.subscribe {
                    showLog("Item: $it")
                }
            }
    }

    private fun probarDebounce() {
        showLog("--------------------DEBOUNCE-----------------")
        val disposable = tilName.editText!!.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .subscribe {
                showLog("onNext: String de búsqueda: $it")
                txtQuery.setText("Query: $it")
            }
    }

    private fun probarDistinct() {
        showLog("--------------------DISTINCT-------------------")
        val numbers = Observable.just(1, 2, 3, 4, 2, 2, 3, 78, 98, 78)
        val disposable = numbers.distinct()
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarElementAt() {
        showLog("----------------ELEMENTAT-------------------")
        val disposable = numbers.elementAt(4)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarFilter() {
        showLog("----------------FILTER-------------------")
        val disposable = numbers.filter {
            it % 2 == 0
        }.subscribe {
            showLog("onNext: $it")
        }
    }

    private fun probarFirst() {
        showLog("----------------FIRST-------------------")
        numbers.first(0)
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    showLog("onSuccess: $t")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun probarIgnoreElements() {
        showLog("----------------IGNOREELEMENTS-------------------")
        val disposable = numbers.ignoreElements()
            .subscribe {
                showLog("Terminado")
            }
    }

    private fun probarLast() {
        showLog("----------------LAST-------------------")
        numbers.last(0)
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    showLog("onSuccess: $t")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun probarSample() {
        showLog("-------------SAMPLE---------------")
        val disposable = Observable.interval(500, TimeUnit.MILLISECONDS)
            .take(10)
            .sample(2, TimeUnit.SECONDS)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarSkip() {
        showLog("----------------------SKIP---------------------")
        val disposable = numbers.skip(4)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarSkipLast() {
        showLog("------------------SKIP LAST------------------")
        val disposable = numbers.skipLast(4)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarTake() {
        showLog("-------------------TAKE-----------------")
        val disposable = numbers
            .take(4)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarTakeLast() {
        showLog("-------------------TAKE LAST-----------------")
        val disposable = numbers
            .takeLast(4)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarCombineLast() {
        showLog("----------------COMBINE LAST--------------")
        val numbers = Observable.interval(400, TimeUnit.MILLISECONDS)
            .take(10)
        val letters = Observable.interval(100, TimeUnit.MILLISECONDS)
            .take(50)

        val disposable = Observable
            .combineLatest<Long, Long, String>(numbers, letters,
                BiFunction { t1, t2 ->
                    "$t1:$t2"
                })
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarJoin() {
        showLog("-------------------JOIN------------------")
        val LEFT_WINDOW_DURATION = 0L
        val RIGHT_WINDOW_DURATION = 0L

        val leftObservable = Observable.interval(70, TimeUnit.MILLISECONDS).take(10)
        val rightObservable = Observable.interval(100, TimeUnit.MILLISECONDS).take(10)

        val disposable = leftObservable.join<Long, Long, Long, String>(
            rightObservable,
            Function {
                Observable.timer(LEFT_WINDOW_DURATION, TimeUnit.MILLISECONDS)
            }, Function {
                Observable.timer(RIGHT_WINDOW_DURATION, TimeUnit.MILLISECONDS)
            }, BiFunction { l, r ->
                showLog("Left: $l, Right: $r")
                "$l:$r"
            })
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarMerge() {
        showLog("-------------------MERGE------------------")
        val observable = Observable.interval(2, TimeUnit.SECONDS)
            .map {
                "Grupo 1 : $it"
            }

        val observable2 = Observable.interval(1, TimeUnit.SECONDS)
            .map {
                "Grupo 2 : $it"
            }

        val disposable = Observable.merge(observable, observable2)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarZip() {
        showLog("------------------ZIP--------------------")
        val observable = Observable.interval(1, TimeUnit.SECONDS)
            .map {
                "Grupo 1:$it"
            }

        val observable2 = Observable.interval(1, TimeUnit.SECONDS)
            .map {
                "Grupo 2:$it"
            }

        val disposable = Observable.zip<String, String, String>(
            observable,
            observable2,
            BiFunction { t1, t2 ->
                "$t1 - $t2"
            })
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarRetry() {
        showLog("---------------------RETRY---------------------")
        val disposable = Observable.create<String> {
            it.onNext("Probando RETRY")
            it.onError(Throwable("Error de prueba"))
        }.retryWhen {
            it.retry()

        }.subscribe({
            showLog("onNext: $it")
        }, {
            showLog("onError: $it")
        }, {
            showLog("onComplete")
        })
    }

    private fun probarDelay() {
        showLog("---------------------Delay---------------------")
        val disposable = numbers.delay(5, TimeUnit.SECONDS)
            .subscribe {
                showLog("onNext: it")
            }
    }

    private fun probarDo() {
        showLog("---------------------DO---------------------")
        val disposable = numbers
            .doOnNext {
                showLog("doOnNext: $it")
            }.doAfterNext {
                showLog("doAfterNext: $it")
            }.doOnComplete {
                showLog("doOnComplete")
            }
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarObserveOnSubscribeOn() {
        showLog("-------ObserveOn - SubscribeOn--------")
        val disposable = Observable.create<String> {
            showLog("Hilo de ejecución Observable: ${Thread.currentThread().name}")
            it.onNext("Emitiendo un item")
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showLog("Hilo de ejecución Observer: ${Thread.currentThread().name}")
            }
    }

    private fun probarTimeInterval() {
        showLog("------------------------TIME INTERVAL-------------------------")
        val disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
            .take(3)
            .timeInterval()
            .subscribe(object : Subject<Timed<Long>>() {
                override fun hasThrowable(): Boolean {
                    return false
                }

                override fun hasObservers(): Boolean {
                    return false
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun getThrowable(): Throwable? {
                    return null
                }

                override fun subscribeActual(observer: Observer<in Timed<Long>>?) {
                }

                override fun onNext(t: Timed<Long>) {
                    showLog("onNext: $t")
                }

                override fun hasComplete(): Boolean {
                    return false
                }

            })
    }

    private fun probarTimeOut() {
        showLog("-------------------------TIME OUT---------------------------")
        val disposable = Observable.timer(1, TimeUnit.SECONDS)
            .timeout(500, TimeUnit.MILLISECONDS)
            .subscribe({
                showLog("onNext: $it")
            }, {
                showLog("onError: ${it.localizedMessage}")
            })
    }

    private fun probarTimeStamp() {
        showLog("----------------------------------------------")
        val observable = Observable.create<String> {
            it.onNext("A")
            it.onNext("B")
            it.onNext("C")
        }

        observable.timestamp()
            .subscribe {
                showLog("$it")
            }
    }

    private fun probarUsing() {
        showLog("------------USING-------------")
        val disposable = Observable.using(
            Callable<String> { "using" },
            Function<String, ObservableSource<Char>> { t ->
                Observable.create {
                    for (c in t.toCharArray()) {
                        it.onNext(c)
                    }
                    it.onComplete()
                }
            }
            , Consumer<String> { t -> showLog("Disposable $t") })
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarAll() {
        showLog("-----------------ALL------------------")
        val disposable = numbers.all {
            it < 8
        }.subscribe { isSuccessful ->
            showLog("onSuccess: $isSuccessful")
        }
    }

    private fun probarAmb() {
        showLog("-----------------AMB------------------")
        val letters = Observable.just("A", "B", "C", "D", "E", "F")

        val disposable = Observable.ambArray(
            numbers.delay(1, TimeUnit.SECONDS),
            letters
        ).subscribe {
            showLog("onNext: $it")
        }
    }

    private fun probarContains() {
        showLog("-----------------CONTAINS------------------")
        val disposable = numbers
            .contains(8)
            .subscribe { isSuccessful ->
                showLog("onSuccess: $isSuccessful")
            }
    }

    private fun probarDefaultIfEmpty() {
        showLog("-----------------DEFAULT IF EMPTY------------------")
        val disposable = Observable.create<Int> {
            val num = 7
            if (num % 2 == 0) {
                it.onNext(num)
            }
            it.onComplete()
        }.defaultIfEmpty(0)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarSequenceEqual() {
        showLog("-----------------SEQUENCE EQUAL------------------")
        val numbers2 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val disposable = Observable.sequenceEqual(numbers, numbers2)
            .subscribe { isSuccess ->
                showLog("onSuccess: $isSuccess")
            }
    }

    private fun probarSkipUntil() {
        showLog("-----------------SKIP UNTIL------------------")
        val numbers = Observable.create<Int> {
            for (i in 0..50) {
                Thread.sleep(100)
                it.onNext(i)
            }
            it.onComplete()
        }

        val timer = Observable.timer(3, TimeUnit.SECONDS)

        val disposable = numbers.skipUntil(timer)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun probarTakeUntil() {
        showLog("-------------TAKE UNTIL----------------")
        val obs1 = Observable.create<Int> {
            for (i in 0..30) {
                Thread.sleep(200)
                it.onNext(i)
            }
            it.onComplete()
        }

        val obs2 = Observable.timer(2, TimeUnit.SECONDS)


        val disposable = obs1.takeUntil(obs2)
            .subscribe {
                showLog("onNext: $it")
            }
    }

    private fun tareaLargaDuracion(): String {
        Thread.sleep(20000L)
        return "Terminado"
    }

    private fun showLog(message: String) {
        Log.d(TAG, message)
    }
}
