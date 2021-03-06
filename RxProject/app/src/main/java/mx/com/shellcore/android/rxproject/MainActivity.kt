package mx.com.shellcore.android.rxproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnClicks()
    }

    private fun setupOnClicks() {
        btnRx00Intro.setOnClickListener {
            startActivity(Intent(this, Rx00IntroActivity::class.java))
        }
        btnRx01Disposable.setOnClickListener {
            startActivity(Intent(this, Rx01DisposableActivity::class.java))
        }
        btnRx02Composite.setOnClickListener {
            startActivity(Intent(this, Rx02CompositeActivity::class.java))
        }
        btnRx03Operadores.setOnClickListener {
            startActivity(Intent(this, Rx03OperatorsActivity::class.java))
        }
        btnRx04TiposOperadores.setOnClickListener {
            startActivity(Intent(this, Rx04ObservableTypesActivity::class.java))
        }
        btnRx05Subjects.setOnClickListener {
            startActivity(Intent(this, Rx05SubjectActivity::class.java))
        }
        btnRx06Bus.setOnClickListener {
            startActivity(Intent(this, Rx06BusActivity::class.java))
        }
        btnRx07Binding.setOnClickListener {
            startActivity(Intent(this, Rx07BindingActivity::class.java))
        }
        btnRx08BackPressure.setOnClickListener {
            startActivity(Intent(this, Rx08BackPressureActivity::class.java))
        }
        btnRx09HotAndCold.setOnClickListener {
            startActivity(Intent(this, Rx09HotAndColdActivity::class.java))
        }
    }
}
