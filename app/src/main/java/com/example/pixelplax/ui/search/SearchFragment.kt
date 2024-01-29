package com.example.pixelplax.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.ui.MovieAdapter
import com.example.pixelplax.MainActivity
import com.example.pixelplax.R
import com.example.pixelplax.databinding.FragmentSearchBinding
import com.example.pixelplax.ui.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var searchView: SearchView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = (activity as MainActivity).findViewById(R.id.searchView)
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    if (newText != null) {
                        searchViewModel.queryChannel.value = newText
                    }
                    binding.rvSearch.visibility =
                        if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
                }
                return true
            }
        })

        getResult()
        setAdapter()
    }

    private fun getResult() {
        searchViewModel.searchResult.observe(viewLifecycleOwner) { movie ->
            lifecycleScope.launch {
                movie.collect { list ->
                    if (list.isNotEmpty()) {
                        movieAdapter.setData(list)
                        binding.apply {
                            tvNotFound.visibility = View.GONE
                            rvSearch.visibility = View.VISIBLE
                        }
                    } else {
                        binding.apply {
                            tvNotFound.visibility = View.VISIBLE
                            rvSearch.visibility = View.GONE
                        }
                        movieAdapter.setData(emptyList())
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        with(binding.rvSearch) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}