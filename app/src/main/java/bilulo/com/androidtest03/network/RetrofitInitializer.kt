package bilulo.com.androidtest03.network

import bilulo.com.androidtest03.network.api.ViaCepApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val loggingInterceptor = HttpLoggingInterceptor()

    val httpClientBuilder = OkHttpClient.Builder()

    lateinit var retrofit : Retrofit

    fun getRetrofitService(): ViaCepApi{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(loggingInterceptor)
        var httpClient = httpClientBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ViaCepApi::class.java)
    }
}