package com.example.pixelplax.ui.series

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.data.source.Resource
import com.example.core.ui.MovieAdapter
import com.example.pixelplax.databinding.FragmentSeriesBinding
import com.example.pixelplax.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!
    private val seriesViewModel: SeriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
                startActivity(intent)
            }

            seriesViewModel.series.observe(viewLifecycleOwner) { series ->
                if (series != null) {
                    when (series) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(series.data)
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

            with(binding.rvSeries) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }
    }
}