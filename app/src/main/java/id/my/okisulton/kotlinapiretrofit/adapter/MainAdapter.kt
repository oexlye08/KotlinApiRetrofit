package id.my.okisulton.kotlinapiretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.okisulton.kotlinapiretrofit.R
import id.my.okisulton.kotlinapiretrofit.model.nested.Result
import kotlinx.android.synthetic.main.list_data.view.*

/**
 * Created by Oki Sulton on 19/05/2021.
 */
class MainAdapter(val results : ArrayList<Result.ResultItem>,
    //untuk menambahkan on click tambahkan listener
    val listener: OnAdapterListener)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from( parent.context ) .inflate(R.layout.list_data, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tv_title.text = result.title
        Glide.with( holder.view )
            .load(result.image)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image_broken)
            .centerCrop()
            .into(holder.view.image)
        holder.view.setOnClickListener {
            listener.onClick( result )
        }

    }

    override fun getItemCount() = results.size

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    //metho setData di main activity
    fun setData( data: List<Result.ResultItem>){
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    //method untuk onClick
    interface OnAdapterListener {
        fun onClick(result: Result.ResultItem)
    }
}