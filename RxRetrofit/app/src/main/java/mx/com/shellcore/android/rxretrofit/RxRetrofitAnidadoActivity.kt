package mx.com.shellcore.android.rxretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import mx.com.shellcore.android.rxretrofit.adapter.ContributorsAdapter
import mx.com.shellcore.android.rxretrofit.model.Contributor
import java.util.ArrayList

class RxRetrofitAnidadoActivity : AppCompatActivity() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private val contributorsAdapter by lazy {
        ContributorsAdapter(contributors)
    }

    private var contributors: List<Contributor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit_anidado)
        setupView()
    }

    private fun setupView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
