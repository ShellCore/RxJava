package mx.com.shellcore.android.rxretrofit.model


import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int
)