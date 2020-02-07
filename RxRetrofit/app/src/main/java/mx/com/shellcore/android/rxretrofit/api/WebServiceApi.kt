package mx.com.shellcore.android.rxretrofit.api

import io.reactivex.Observable
import io.reactivex.Single
import mx.com.shellcore.android.rxretrofit.model.Contributor
import mx.com.shellcore.android.rxretrofit.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServiceApi {

    @GET("users/{user}/repos")
    fun getReposForUser(@Path("user") user: String): Call<List<GithubRepo>>

    @GET("users/{user}/repos")
    fun getReposForUserRx(@Path("user") user: String): Single<List<GithubRepo>>

    @GET("repos/{user}/{repoName}/contributors")
    fun getRepoContributorsForUserAndRepoRx(@Path("user") user: String, @Path("repoName") repoName: String): Observable<List<Contributor>>
}