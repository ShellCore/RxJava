package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_rx07_binding.*

class Rx07BindingActivity : AppCompatActivity() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx07_binding)

        btnRxBinding.setOnClickListener {
            "onClick normal".showLog()
        }

        compositeDisposable.add(btnRxBinding2.clicks().subscribe {
            "onClick utilizando Rx".showLog()
        })

        // Forma antigua para la implementación de un escuchador de cambios en un campo de texto
        /*tilRxBinding.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })*/
        compositeDisposable.addAll(tilRxBinding.editText!!.textChanges()
            .subscribe {
                "onTextChanged: $it".showLog()
            })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
