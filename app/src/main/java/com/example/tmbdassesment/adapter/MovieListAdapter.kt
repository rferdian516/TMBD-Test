package com.example.tmbdassesment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmbdassesment.R
import com.example.tmbdassesment.databinding.ItemCardBinding
import com.example.tmbdassesment.helper.Constant
import com.example.tmbdassesment.model.MovieListModel

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
    private var list = ArrayList<MovieListModel>()
    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val bindView = ItemCardBinding.bind(itemView)

        fun bind(data: MovieListModel) {
            with(bindView) {
                movieTitle.text = data.title
                Glide.with(itemView)
                    .load(Constant.IMG_URL+data.poster_path)
                    .into(movieImage)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MovieViewHolder {
       return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(posts: ArrayList<MovieListModel>) {
        list.addAll(posts)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}