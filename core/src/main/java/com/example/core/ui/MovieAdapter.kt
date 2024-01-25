package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListCardBinding
import com.example.core.domain.model.Movie

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
    inner class MyViewHolder(private val binding: ItemListCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie){
            with(binding){
                tvTitle.text = data.name
                val average = String.format("%.1f", data.voteAverage)
                tvRating.text = average
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.base_image_url, data.posterPath))
                    .into(ivCard)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding = ItemListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Movie>?){
        if (data == null)return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}
