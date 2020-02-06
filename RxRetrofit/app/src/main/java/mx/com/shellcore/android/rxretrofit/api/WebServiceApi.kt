package mx.com.shellcore.android.rxretrofit.api

import io.reactivex.Observable
import mx.com.shellcore.android.rxretrofit.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServiceApi {

    @GET("users/{user}/repos")
    fun getReposForUser(@Path("user") user: String): Call<List<GithubRepo>>

    @GET("users/{user}/repos")
    fun getReposForUserRx(@Path("user") user: String): Observable<List<GithubRepo>>
}