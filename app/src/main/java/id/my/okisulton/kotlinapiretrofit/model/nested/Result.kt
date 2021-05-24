package id.my.okisulton.kotlinapiretrofit.model.nested

/**
 * Created by Oki Sulton on 19/05/2021.
 */
data class Result( val result: ArrayList<ResultItem>){
    data class ResultItem (val id: Int, val title: String, val image: String)
}
