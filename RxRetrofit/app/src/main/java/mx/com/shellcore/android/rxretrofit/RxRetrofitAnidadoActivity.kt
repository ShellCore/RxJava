package mx.com.shellcore.android.rxretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_retrofit_anidado.*
import mx.com.shellcore.android.rxretrofit.adapter.ContributorsAdapter
import mx.com.shellcore.android.rxretrofit.api.WebService
import mx.com.shellcore.android.rxretrofit.model.Contributor
import java.util.*
import kotlin.collections.ArrayList

class RxRetrofitAnidadoActivity : AppCompatActivity() {

    companion object {
        private const val USER = "JakeWharton"
    }

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
        peticionesAnidadasServidorRx()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun setupView() {
        recContributors.apply {
            layoutManager = LinearLayoutManager(
                this@RxRetrofitAnidadoActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = contributorsAdapter
        }
    }

    private fun peticionesAnidadasServidorRx() {
        compositeDisposable.add(WebService.getInstance()
            .createService()
            .getReposForUserRx(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMap { Observable.fromIterable(it) }
            .flatMap {
                WebService.getInstance()
                    .createService()
                    .getRepoContributorsForUserAndRepoRx(USER, it.name)
                    .subscribeOn(Schedulers.io())
            }
            .flatMapIterable { it }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    (contributors as ArrayList).add(it)
                    contributorsAdapter.setContributors(contributors)
                },
                { Log.e("NestedQuery", it.localizedMessage!!) }
            )
        )
    }
}
