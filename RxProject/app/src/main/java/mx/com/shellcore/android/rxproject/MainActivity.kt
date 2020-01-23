package mx.com.shellcore.android.rxproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnclicks()
    }

    private fun setupOnclicks() {
        btnRx00Intro.setOnClickListener {
            startActivity(Intent(this, Rx00IntroActivity::class.java))
        }
    }
}
