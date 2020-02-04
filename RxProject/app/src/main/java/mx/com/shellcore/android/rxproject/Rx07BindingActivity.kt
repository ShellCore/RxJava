package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_rx07_binding.*

class Rx07BindingActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx07_binding)

        btnRxBinding.setOnClickListener {
            "onClick normal".showLog()
        }

        disposable = btnRxBinding2.clicks().subscribe {
            "onClick utilizando Rx".showLog()
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
