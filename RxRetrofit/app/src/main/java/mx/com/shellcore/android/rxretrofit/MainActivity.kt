package mx.com.shellcore.android.rxretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRxRetrofit.setOnClickListener {
            startActivity(Intent(this, RxRetrofitActivity::class.java))
        }

        btnRxRetrofitNested.setOnClickListener {
            startActivity(Intent(this, RxRetrofitAnidadoActivity::class.java))
        }
    }
}
