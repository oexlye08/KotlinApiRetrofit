package id.my.okisulton.kotlinapiretrofit.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Oki Sulton on 19/05/2021.
 */
object ApiServices {
    val BASE_URL: String by lazy { "https://demo.lazday.com/rest-api-sample/" }
    val endpoint: ApiEndpoint
        get() {
            //interceptor
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor( interceptor )
                .build()

            //retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl( BASE_URL )
                    //add client
                .client(client)
                    //batas tanpa client
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create( ApiEndpoint::class.java )
        }
}