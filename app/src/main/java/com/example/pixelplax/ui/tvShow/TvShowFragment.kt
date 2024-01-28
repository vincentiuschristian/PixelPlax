package com.example.pixelplax.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.data.source.Resource
import com.example.core.ui.MovieAdapter
import com.example.pixelplax.R
import com.example.pixelplax.databinding.FragmentTvShowBinding
import com.example.pixelplax.ui.detail.DetailActivity
import com.example.pixelplax.ui.setting.SettingActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)

        binding.topBar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.nav_favorite -> {
                    startActivity(
                        Intent(
                            requireContext(),
                            Class.forName("com.example.favorite.FavoriteActivity")
                        )
                    )
                    true
                }

                R.id.nav_settings -> {
                    startActivity(Intent(requireContext(), SettingActivity::class.java))
                    true
                }

                else -> false
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
                startActivity(intent)
            }

            tvShowViewModel.tvShow.observe(viewLifecycleOwner) { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(tvShow.data)
                        }

                        is Resource.Error -> {
                            binding.apply {
                                progressBar.visibility = View.GONE
                                viewError.root.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }

            setAdapter()
        }
    }

    private fun setAdapter() {
        with(binding.rvTvShow) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}