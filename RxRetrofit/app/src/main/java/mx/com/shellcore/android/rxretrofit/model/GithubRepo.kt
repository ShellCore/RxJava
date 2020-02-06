package mx.com.shellcore.android.rxretrofit.model


import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("language")
    var language: String = "No languaje",
    @SerializedName("name")
    var name: String = "No name",
    @SerializedName("stargazers_count")
    var stargazersCount: Int = 0
)