package mx.com.shellcore.android.rxretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_rx_retrofit.*
import mx.com.shellcore.android.rxretrofit.adapter.RepositoryAdapter
import mx.com.shellcore.android.rxretrofit.model.GithubRepo
import java.util.ArrayList

class RxRetrofitActivity : AppCompatActivity() {

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val reposAdapter: RepositoryAdapter by lazy { RepositoryAdapter(githubRepos) }

    private var githubRepos: List<GithubRepo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit)
        setupView()
    }

    private fun setupView() {
        recRepos.apply {
            layoutManager = LinearLayoutManager(this@RxRetrofitActivity, LinearLayoutManager.VERTICAL, false)
            adapter = reposAdapter
        }
    }
}
