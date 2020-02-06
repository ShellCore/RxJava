package mx.com.shellcore.android.rxretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_rx_retrofit.*
import mx.com.shellcore.android.rxretrofit.adapter.RepositoryAdapter
import mx.com.shellcore.android.rxretrofit.api.WebService
import mx.com.shellcore.android.rxretrofit.model.GithubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RxRetrofitActivity : AppCompatActivity() {

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val reposAdapter: RepositoryAdapter by lazy { RepositoryAdapter(githubRepos) }

    private var githubRepos: List<GithubRepo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit)
        setupView()
        getListWidthoutRx()
    }

    private fun setupView() {
        recRepos.apply {
            layoutManager = LinearLayoutManager(this@RxRetrofitActivity, LinearLayoutManager.VERTICAL, false)
            adapter = reposAdapter
        }
    }

    private fun getListWidthoutRx() {
        val call = WebService.getInstance()
            .createService()
            .getReposForUser("JakeWharton")

        call.enqueue(object : Callback<List<GithubRepo>> {
            override fun onResponse(
                call: Call<List<GithubRepo>>,
                response: Response<List<GithubRepo>>
            ) {
                githubRepos = response.body()!!
                reposAdapter.setRepos(githubRepos)
            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                Log.e("GetReposForUser", t.localizedMessage!!)
            }
        })
    }
}
