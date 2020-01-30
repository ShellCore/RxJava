package mx.com.shellcore.android.rxproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import hu.akarnokd.rxjava2.math.MathObservable
import io.reactivex.*
import io.reactivex.Observable.*
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

class Rx03OperatorsActivity : AppCompatActivity() {

    private val numbers = just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val numbersArray = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    private val obs = fromArray(1, 34, 43, 1, 5, 7, 78, 151, 546, 1, 2, 5, 6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx03_operadores)

        probeJust()
        probeJustArray()
        probeFromArray()
        probeRange()
        probeRepeat()
        probeCreate()
        probeInterval()
        probeCreateException()
        probeLargeDuration()
        probeLargeDurationLambda()
        probeBuffer()
        probeMap()
        probeFlatMap()
        probeGroupBy()
        probeScan()
        probeWindow()
        probeDebounce()
        probeDistinct()
        probeElementAt()
        probeFilter()
        probeFirst()
        probeIgnoreElements()
        probeLast()
        probeSample()
        probeSkip()
        probeSkipLast()
        probeTake()
        probeTakeLast()
        probeCombineLast()
        probeJoin()
        probeMerge()
        probeZip()
        probeRetry()
        probeDelay()
        probeDo()
        probeObserveOnSubscribeOn()
        probeTimeInterval()
        probeTimeOut()
        probeTimeStamp()
        probeUsing()
        probeAll()
        probeAmb()
        probeContains()
        probeDefaultIfEmpty()
        probeSequenceEqual()
        probeSkipUntil()
        probeTakeUntil()
        probeTakeWhile()
        probeAverage()
        probeCount()
        probeMax()
        probeMin()
        probeSum()
        probeReduce()
    }

    private fun probeJust() {
        "JUST".showTitle()
        numbers.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(number: Int) {
                    "Just -> onNext($number)".showLog()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun probeJustArray() {
        "JUST_ARRAY".showTitle()
        just(numbersArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Array<String>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Array<String>) {
                    "JustArray -> onNext (${t.size})".showLog()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun probeFromArray() {
        "FROM ARRAY".showTitle()
        fromArray(*numbersArray) // Así se marca para que Kotlin reconozca la variable numbers como String[], y pueda usarse fromArray
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {}

                override fun onComplete() {}

                override fun onNext(t: String) {
                    "FromArray -> onNext(${t})".showLog()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun probeRange() {
        "RANGE".showTitle()
        range(7, 17)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(integer: Int) {
                    "Range -> $integer".showLog()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun probeRepeat() {
        "REPEAT".showLog()
        range(10, 3)
            .repeat(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(integer: Int) {
                    "Repeat -> $integer".showLog()
                }

                override fun onError(e: Throwable) {}

            })
    }

    private fun probeCreate() {
        "CREATE".showLog()
        create(ObservableOnSubscribe<String> {
            try {
                "Subscribe. Thread: ${Thread.currentThread().name}".showLog()
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
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(s: String) {
                    "onNext: $s. Thread: ${Thread.currentThread().name}".showLog()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun probeInterval() {
        "INTERVAL".showLog()
        interval(1, TimeUnit.SECONDS)
            .take(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(s: Long) {
                    "Interval: $s".showLog()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun probeCreateException() {
        "CREATE EXCEPTION".showLog()
        create<Int> {
            try {
                it.onNext(15 / 3)
                @Suppress("DIVISION_BY_ZERO")
                it.onNext(3 / 0)
            } catch (e: Exception) {
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Int) {
                    "onNext: $t".showLog()
                }

                override fun onError(e: Throwable) {
                    "onError: ${e.localizedMessage}".showLog()
                }
            })
    }

    private fun probeLargeDuration() {
        "CREATE EXCEPTION".showLog()
        create<String> {
            try {
                "Subscribe. Thread ${Thread.currentThread().name}".showLog()
                it.onNext(tareaLargaDuracion())
            } catch (e: Exception) {
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: String) {
                    "Subscribe. Thread ${Thread.currentThread().name}".showLog()
                    "onNext: $t".showLog()
                }

                override fun onError(e: Throwable) {
                    "OnError: ${e.localizedMessage}".showLog()
                }
            })
    }

    @SuppressLint("CheckResult")
    private fun probeLargeDurationLambda() {
        "CREATE EXCEPTION".showLog()
        create<String> {
            try {
                "Subscribe. Thread: ${Thread.currentThread().name}".showLog()
                it.onNext(tareaLargaDuracion())
            } catch (e: Exception) {
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ s ->
                "Subscribe. Thread: ${Thread.currentThread().name}".showLog()
                "onNext: $s".showLog()
            }, {
                "OnError: ${it.localizedMessage}".showLog()
            })
    }

    @SuppressLint("CheckResult")
    private fun probeBuffer() {
        "BUFFER".showLog()
        numbers.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(3)
            .subscribe {
                "Buffer onNext".showLog()
                it.iterator().forEach { item ->
                    "Buffer item: $item".showLog()
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun probeMap() {
        "MAP".showLog()
        val employees = Empleado.getListaEmpleados()

        fromArray(employees)
            .map {
                val names = ArrayList<String>()
                for (employee in it) {
                    names.add(employee.nombre)
                }
                names
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                "MapItems: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeFlatMap() {
        "FLATMAP".showLog()
        just("item2", "item3")
            .flatMap {
                "Inside the FlatMap $it".showLog()
                just("$it 1", "$it 2", "$it 3")
            }
            .subscribe {
                "Result: $it".showLog()
            }

    }

    @SuppressLint("CheckResult")
    private fun probeGroupBy() {
        "GROUPBY".showLog()
        val groupObservable: Observable<GroupedObservable<String, Int>> = numbers.groupBy {
            if (it % 2 == 0) "PAR" else "IMPAR"
        }
        groupObservable.subscribe { stringIntGroupedObservable ->
            stringIntGroupedObservable.subscribe {
                if (stringIntGroupedObservable.key == "PAR") {
                    "PAR: $it".showLog()
                } else {
                    "IMPAR: $it".showLog()
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun probeScan() {
        "SCAN".showLog()
        numbers
            .scan { t1: Int, t2: Int ->
                t1 + t2
            }
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeWindow() {
        "WINDOW".showLog()
        range(1, 150)
            .window(3)
            .subscribe { windowObservable ->
                "Siguiente ventana".showLog()
                windowObservable.subscribe {
                    "Item: $it".showLog()
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun probeDebounce() {
        "DEBOUNCE".showLog()
        tilName.editText!!.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .subscribe {
                "onNext: Query string: $it".showLog()
                val message = "Query $it"
                txtQuery.text = message
            }
    }

    @SuppressLint("CheckResult")
    private fun probeDistinct() {
        "DISTINCT".showLog()
        numbers.distinct()
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeElementAt() {
        "ELEMENTAT".showLog()
        numbers.elementAt(4)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeFilter() {
        "FILTER".showLog()
        numbers.filter {
            it % 2 == 0
        }.subscribe {
            "onNext: $it".showLog()
        }
    }

    private fun probeFirst() {
        "FIRST".showLog()
        numbers.first(0)
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    "onSuccess: $t".showLog()
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {}
            })
    }

    @SuppressLint("CheckResult")
    private fun probeIgnoreElements() {
        "IGNOREELEMENTS".showLog()
        numbers.ignoreElements()
            .subscribe {
                "Terminado".showLog()
            }
    }

    private fun probeLast() {
        "LAST".showLog()
        numbers.last(0)
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    "onSuccess: $t".showLog()
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {}
            })
    }

    @SuppressLint("CheckResult")
    private fun probeSample() {
        "SAMPLE".showLog()
        interval(500, TimeUnit.MILLISECONDS)
            .take(10)
            .sample(2, TimeUnit.SECONDS)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeSkip() {
        "SKIP".showLog()
        numbers.skip(4)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeSkipLast() {
        "SKIP LAST".showLog()
        numbers.skipLast(4)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeTake() {
        "TAKE".showLog()
        numbers
            .take(4)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeTakeLast() {
        "TAKE LAST".showLog()
        numbers
            .takeLast(4)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeCombineLast() {
        "COMBINE LAST".showLog()
        val numbers = interval(400, TimeUnit.MILLISECONDS)
            .take(10)
        val letters = interval(100, TimeUnit.MILLISECONDS)
            .take(50)

        combineLatest<Long, Long, String>(numbers, letters,
            BiFunction { t1, t2 ->
                "$t1:$t2"
            })
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeJoin() {
        "JOIN".showLog()
        val leftWindowDuration = 0L
        val rightWindowDuration = 0L

        val leftObservable = interval(70, TimeUnit.MILLISECONDS).take(10)
        val rightObservable = interval(100, TimeUnit.MILLISECONDS).take(10)

        leftObservable.join<Long, Long, Long, String>(
            rightObservable,
            Function {
                timer(leftWindowDuration, TimeUnit.MILLISECONDS)
            }, Function {
                timer(rightWindowDuration, TimeUnit.MILLISECONDS)
            }, BiFunction { l, r ->
                "Left: $l, Right: $r".showLog()
                "$l:$r"
            })
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeMerge() {
        "MERGE".showLog()
        val observable = interval(2, TimeUnit.SECONDS)
            .map {
                "Grupo 1 : $it"
            }

        val observable2 = interval(1, TimeUnit.SECONDS)
            .map {
                "Grupo 2 : $it"
            }

        merge(observable, observable2)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeZip() {
        "ZIP".showLog()
        val observable = interval(1, TimeUnit.SECONDS)
            .map {
                "Grupo 1:$it"
            }

        val observable2 = interval(1, TimeUnit.SECONDS)
            .map {
                "Grupo 2:$it"
            }

        zip<String, String, String>(
            observable,
            observable2,
            BiFunction { t1, t2 ->
                "$t1 - $t2"
            })
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeRetry() {
        "RETRY".showLog()
        create<String> {
            it.onNext("Probando RETRY")
            it.onError(Throwable("Error de prueba"))
        }.retryWhen {
            it.retry()

        }.subscribe({
            "onNext: $it".showLog()
        }, {
            "onError: $it".showLog()
        }, {
            "onComplete".showLog()
        })
    }

    @SuppressLint("CheckResult")
    private fun probeDelay() {
        "Delay".showLog()
        numbers.delay(5, TimeUnit.SECONDS)
            .subscribe {
                "onNext: it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeDo() {
        "DO".showLog()
        numbers
            .doOnNext {
                "doOnNext: $it".showLog()
            }.doAfterNext {
                "doAfterNext: $it".showLog()
            }.doOnComplete {
                "doOnComplete".showLog()
            }
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeObserveOnSubscribeOn() {
        "ObserveOn - SubscribeOn".showLog()
        create<String> {
            "Hilo de ejecución Observable: ${Thread.currentThread().name}".showLog()
            it.onNext("Emitiendo un item")
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                "Hilo de ejecución Observer: ${Thread.currentThread().name}".showLog()
            }
    }

    private fun probeTimeInterval() {
        "TIME INTERVAL".showLog()
        interval(1000, TimeUnit.MILLISECONDS)
            .take(3)
            .timeInterval()
            .subscribe(object : Subject<Timed<Long>>() {
                override fun hasThrowable(): Boolean {
                    return false
                }

                override fun hasObservers(): Boolean {
                    return false
                }

                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {}

                override fun getThrowable(): Throwable? {
                    return null
                }

                override fun subscribeActual(observer: Observer<in Timed<Long>>?) {}

                override fun onNext(t: Timed<Long>) {
                    "onNext: $t".showLog()
                }

                override fun hasComplete(): Boolean {
                    return false
                }

            })
    }

    @SuppressLint("CheckResult")
    private fun probeTimeOut() {
        "TIME OUT".showLog()
        timer(1, TimeUnit.SECONDS)
            .timeout(500, TimeUnit.MILLISECONDS)
            .subscribe({
                "onNext: $it".showLog()
            }, {
                "onError: ${it.localizedMessage}".showLog()
            })
    }

    @SuppressLint("CheckResult")
    private fun probeTimeStamp() {
        "TIMESTAMP".showTitle()
        val observable = create<String> {
            it.onNext("A")
            it.onNext("B")
            it.onNext("C")
        }

        observable.timestamp()
            .subscribe {
                "$it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeUsing() {
        "USING".showLog()
        using(
            Callable<String> { "using" },
            Function<String, ObservableSource<Char>> { t ->
                create {
                    for (c in t.toCharArray()) {
                        it.onNext(c)
                    }
                    it.onComplete()
                }
            }
            , Consumer<String> { t -> "Disposable $t".showLog() })
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeAll() {
        "ALL".showLog()
        numbers.all {
            it < 8
        }.subscribe { isSuccessful ->
            "onSuccess: $isSuccessful".showLog()
        }
    }

    @SuppressLint("CheckResult")
    private fun probeAmb() {
        "AMB".showLog()
        val letters = just("A", "B", "C", "D", "E", "F")

        ambArray(
            numbers.delay(1, TimeUnit.SECONDS),
            letters
        ).subscribe {
            "onNext: $it".showLog()
        }
    }

    @SuppressLint("CheckResult")
    private fun probeContains() {
        "CONTAINS".showLog()
        numbers
            .contains(8)
            .subscribe { isSuccessful ->
                "onSuccess: $isSuccessful".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeDefaultIfEmpty() {
        "DEFAULT IF EMPTY".showLog()
        var num = 8
        println(num)
        create<Int> {
            num = 7
            if (num % 2 == 0) {
                it.onNext(num)
            }
            it.onComplete()
        }.defaultIfEmpty(0)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeSequenceEqual() {
        "SEQUENCE EQUAL".showLog()
        val numbers2 = just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        sequenceEqual(numbers, numbers2)
            .subscribe { isSuccess ->
                "onSuccess: $isSuccess".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeSkipUntil() {
        "SKIP UNTIL".showLog()
        val numbers = create<Int> {
            for (i in 0..50) {
                Thread.sleep(100)
                it.onNext(i)
            }
            it.onComplete()
        }

        val timer = timer(3, TimeUnit.SECONDS)

        numbers.skipUntil(timer)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeTakeUntil() {
        "TAKE UNTIL".showLog()
        val obs1 = create<Int> {
            for (i in 0..30) {
                Thread.sleep(200)
                it.onNext(i)
            }
            it.onComplete()
        }

        val obs2 = timer(2, TimeUnit.SECONDS)

        obs1.takeUntil(obs2)
            .subscribe {
                "onNext: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeTakeWhile() {
        "TAKE WHILE".showLog()
        create<Int> {
            for (i in 0..30) {
                Thread.sleep(200)
                it.onNext(i)
            }
            it.onComplete()
        }.takeWhile {
            it < 12
        }.subscribe {
            "onNext: $it".showLog()
        }
    }

    @SuppressLint("CheckResult")
    private fun probeAverage() {
        "AVERAGE".showLog()
        MathObservable.averageDouble(obs)
            .subscribe {
                "Average: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeCount() {
        "COUNT".showLog()
        obs.count()
            .subscribe { t ->
                "Count: $t".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeMax() {
        "MAX".showLog()
        MathObservable.max(obs)
            .subscribe {
                "Max: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeMin() {
        "MIN".showLog()
        MathObservable.min(obs)
            .subscribe {
                "Min: $it".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeSum() {
        "SUM".showLog()
        MathObservable.sumInt(obs)
            .subscribe { t ->
                "Sum: $t".showLog()
            }
    }

    @SuppressLint("CheckResult")
    private fun probeReduce() {
        "REDUCE".showLog()

        just(2, 2, 2, 2)
            .reduce { t1, t2 -> t1 * t2 }
            .subscribe {
                "Result: $it".showLog()
            }
    }

    private fun tareaLargaDuracion(): String {
        Thread.sleep(20000L)
        return "Terminado"
    }
}
