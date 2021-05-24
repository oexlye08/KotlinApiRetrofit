package id.my.okisulton.kotlinapiretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.my.okisulton.kotlinapiretrofit.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_data.view.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.title = intent.getStringExtra("title")
        Glide.with( this )
            .load(intent.getStringExtra("image"))
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image_broken)
            .centerCrop()
            .into(image_detail)
    }
}