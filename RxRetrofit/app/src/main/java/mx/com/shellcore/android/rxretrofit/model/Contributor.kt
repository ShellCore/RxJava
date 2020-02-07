package mx.com.shellcore.android.rxretrofit.model


import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("contributions")
    val contributions: Int,
    @SerializedName("login")
    val login: String
)