package mx.com.shellcore.android.rxretrofit.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WebService {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        private var instance: WebService? = null

        fun getInstance(): WebService {
            if (instance == null) {
                instance = WebService()
            }
            return instance as WebService
        }
    }

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val httpClientBuilder by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createService(): WebServiceApi = retrofit.create(WebServiceApi::class.java)
}