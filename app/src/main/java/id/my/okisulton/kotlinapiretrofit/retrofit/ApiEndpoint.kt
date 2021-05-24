package id.my.okisulton.kotlinapiretrofit.retrofit

import id.my.okisulton.kotlinapiretrofit.model.MainModel
import id.my.okisulton.kotlinapiretrofit.model.nested.Result
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Oki Sulton on 19/05/2021.
 */
interface ApiEndpoint {
    @GET("photos")
    fun getPhotos(): Call<List<MainModel>>

    @GET("data.php")
    fun getData(): Call<Result>
}
