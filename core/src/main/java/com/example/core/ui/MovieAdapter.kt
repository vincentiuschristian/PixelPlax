package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListCardBinding
import com.example.core.domain.model.Movie
import com.example.core.utils.MovieDiffCallback
import java.util.Locale

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    inner class MyViewHolder(private val binding: ItemListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                tvTitle.text = data.originalName
                val average = "%.1f".format(Locale.US, data.voteAverage)
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
        val binding =
            ItemListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    fun setData(data: List<Movie>?) {
        if (data == null) return
        val diffCallback = MovieDiffCallback(listData, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listData.clear()
        listData.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}
