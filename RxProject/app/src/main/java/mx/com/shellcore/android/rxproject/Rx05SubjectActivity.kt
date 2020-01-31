package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class Rx05SubjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx05_subject)

        probePublishSubject()
    }

    private fun probePublishSubject() {
        "PUBLISH_SUBJECT".showTitle()
        val source = PublishSubject.create<String>()
        val obs = object : Observer<String> {
            override fun onComplete() {
                "Primer observer. onComplete".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: String) {
                "Primer observer. onNext: $t".showLog()
            }

            override fun onError(e: Throwable) {}
        }

        val obs2 = object : Observer<String> {
            override fun onComplete() {
                "Segundo observer. onComplete".showLog()
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: String) {
                "Segundo observer. onNext: $t".showLog()
            }

            override fun onError(e: Throwable) {}
        }

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
}
