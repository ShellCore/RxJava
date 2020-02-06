package mx.com.shellcore.android.rxretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_retrofit.*
import mx.com.shellcore.android.rxretrofit.adapter.RepositoryAdapter
import mx.com.shellcore.android.rxretrofit.api.WebService
import mx.com.shellcore.android.rxretrofit.model.GithubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RxRetrofitActivity : AppCompatActivity() {

    companion object {
        private const val USER = "JakeWharton"
    }

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val reposAdapter: RepositoryAdapter by lazy { RepositoryAdapter(githubRepos) }

    private var githubRepos: List<GithubRepo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit)
        setupView()
//        getListWithoutRx()
//        getListWithRx()
//        getListWithRxInverse()
//        getListWithRxWithFilter()
//        getListWithRxOperators()
        getListWithRxOrderByStars()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun setupView() {
        recRepos.apply {
            layoutManager =
                LinearLayoutManager(this@RxRetrofitActivity, LinearLayoutManager.VERTICAL, false)
            adapter = reposAdapter
        }
    }

    private fun getListWithoutRx() {
        val call = WebService.getInstance()
            .createService()
            .getReposForUser(USER)

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

    private fun getListWithRx() {
        compositeDisposable.add(WebService.getInstance()
            .createService()
            .getReposForUserRx(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                reposAdapter.setRepos(it)
            }, {
                Log.e("GetReposForUser", it.localizedMessage!!)
            })
        )
    }

    private fun getListWithRxInverse() {
        compositeDisposable.add(WebService.getInstance()
            .createService()
            .getReposForUserRx(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Collections.sort(it) { o1, o2 ->
                    o2.name.compareTo(o1.name)
                }
                it
            }
            .subscribe({
                reposAdapter.setRepos(it)
            }, {
                Log.e("GetReposForUser", it.localizedMessage!!)
            })
        )
    }

    private fun getListWithRxWithFilter() {
        compositeDisposable.add(WebService.getInstance()
            .createService()
            .getReposForUserRx(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it }
            .filter { it.language == "Kotlin" }
            .subscribe({
                (githubRepos as ArrayList).add(it)
                reposAdapter.setRepos(githubRepos)
            }, {
                Log.e("GetReposForUser", it.localizedMessage!!)
            })
        )
    }

    private fun getListWithRxOperators() {
        compositeDisposable.add(WebService.getInstance()
            .createService()
            .getReposForUserRx(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it }
            .filter { it.language == "Java" }
//            .take(3)
//            .elementAt(3)
//            .first(GithubRepo())
            .skip(3)
            .subscribe({
                (githubRepos as ArrayList).add(it)
                reposAdapter.setRepos(githubRepos)
            }, {
                Log.e("GetReposForUser", it.localizedMessage!!)
            })
        )
    }

    private fun getListWithRxOrderByStars() {
        compositeDisposable.add(WebService.getInstance()
            .createService()
            .getReposForUserRx(USER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it }
            .toSortedList { o1, o2 -> o2.stargazersCount - o1.stargazersCount }
            .subscribe({
                reposAdapter.setRepos(it)
            }, {
                Log.e("GetReposForUser", it.localizedMessage!!)
            })
        )
    }
}
