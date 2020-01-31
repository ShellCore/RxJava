package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class Rx05SubjectActivity : AppCompatActivity() {

    private val obs = object : Observer<String> {
        override fun onComplete() {
            "Primer observer. onComplete".showLog()
        }

        override fun onSubscribe(d: Disposable) {}

        override fun onNext(t: String) {
            "Primer observer. onNext: $t".showLog()
        }

        override fun onError(e: Throwable) {}
    }
    private val obs2 = object : Observer<String> {
        override fun onComplete() {
            "Segundo observer. onComplete".showLog()
        }

        override fun onSubscribe(d: Disposable) {}

        override fun onNext(t: String) {
            "Segundo observer. onNext: $t".showLog()
        }

        override fun onError(e: Throwable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx05_subject)

//        probePublishSubject()
//        probeReplySubject()
        probeAsyncSubject()
    }

    private fun probePublishSubject() {
        "PUBLISH_SUBJECT".showTitle()
        val source = PublishSubject.create<String>()
        source.apply {
            subscribe(obs)
            onNext("A")
            onNext("B")
            onNext("C")
            subscribe(obs2)
            onNext("1")
            onNext("2")
            onNext("3")
            onComplete()
        }
    }

    private fun probeReplySubject() {
        "REPLY_SUBJECT".showTitle()
        val source = ReplaySubject.create<String>()
        source.apply {
            subscribe(obs)
            onNext("A")
            onNext("B")
            onNext("C")
            subscribe(obs2)
            onNext("1")
            onNext("2")
            onNext("3")
            onComplete()
        }
    }

    private fun probeAsyncSubject() {
        "ASYNC_SUBJECT".showTitle()
        val source = AsyncSubject.create<String>()
        source.apply {
            subscribe(obs)
            onNext("A")
            onNext("B")
            onNext("C")
            onNext("1")
            onNext("2")
            onNext("3")
            onComplete()
            subscribe(obs2)
        }
    }
}
