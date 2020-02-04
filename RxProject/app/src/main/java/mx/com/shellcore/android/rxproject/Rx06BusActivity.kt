package mx.com.shellcore.android.rxproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_rx06_bus.*

class Rx06BusActivity : AppCompatActivity() {

    private val transaction: FragmentTransaction by lazy {
        supportFragmentManager.beginTransaction()
    }

    private val fragment: Rx06BusFragment by lazy { Rx06BusFragment() }

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx06_bus)
        transaction.add(R.id.frmList, fragment)
        transaction.commit()

        btnBus.setOnClickListener {
            Rx06Bus.getInstance()
                .setEvents("Hola, soy el bus")
        }

        val observable = Rx06Bus.getInstance()
            .getEvents()
        compositeDisposable.add(observable.subscribe {
            "Rx06Activity: $it".showLog()
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
