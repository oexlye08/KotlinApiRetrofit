package id.my.okisulton.kotlinapiretrofit.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.my.okisulton.kotlinapiretrofit.R
import id.my.okisulton.kotlinapiretrofit.adapter.MainAdapter
import id.my.okisulton.kotlinapiretrofit.model.MainModel
import id.my.okisulton.kotlinapiretrofit.model.nested.Result
import id.my.okisulton.kotlinapiretrofit.retrofit.ApiServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
//        getDataFromApi()
        setupRv()
        getNewData()
    }

    private fun setupRv() {
        mainAdapter = MainAdapter(arrayListOf(),
            //tambahkan ini jika ada onclick
            object : MainAdapter.OnAdapterListener{
                override fun onClick(result: Result.ResultItem) {
                    Toast.makeText(this@MainActivity, result.title, Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(applicationContext, DetailActivity::class.java)
                            .putExtra("title", result.title)
                            .putExtra("image", result.image)
                    )
                }

            }
        )
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromApi() {
        ApiServices.endpoint.getPhotos()
            .enqueue(object : Callback<List<MainModel>>{
                override fun onResponse(
                    call: Call<List<MainModel>>,
                    response: Response<List<MainModel>>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()
                        showDetail(result!!)
                        Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<MainModel>>, t: Throwable) {
                    printLog(t.toString())
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                }

            })

    }

    private fun getNewData() {
        progressBar.visibility = View.VISIBLE
        ApiServices.endpoint.getData()
            .enqueue(object : Callback<Result>{
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful){
                        showDetailNew(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showDetail(datas: List<MainModel>){
        for (data in datas){
            printLog( "title : ${data.title}")
            printLog( "url : ${data.url}")
        }
    }

    private fun showDetailNew(data : Result){
        val results = data.result
        mainAdapter.setData(results)
//        for (result in results){
//            printLog("title : ${result.title}")
//            printLog("image : ${result.image}")
//        }
    }
}