package com.example.pixelplax.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.pixelplax.R
import com.example.pixelplax.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail =  getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)
        setData(detail)
    }

    private fun setData(data: Movie?){
        data?.let {
            binding.apply {
                tvTitle.text = data.name
                releaseDate.text = data.firstAirDate
                tvOverview.text = data.overview
                tvRating.text = data.voteAverage.toString()
                tvVoteCount.text = data.voteCount.toString()
                tvPopularity.text = data.popularity.toString()
                Glide.with(applicationContext)
                    .load(resources.getString(R.string.base_image_url, data.posterPath))
                    .into(ivPoster)

                var statusFavorite = data.isFavorite
                setFavorite(statusFavorite)
                fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    setFavorite(statusFavorite)
                }

            }
        }
    }

    private fun setFavorite(status: Boolean){
        if (status){
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.heart_selected
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.heart
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}