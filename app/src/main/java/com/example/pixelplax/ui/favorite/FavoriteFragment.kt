package com.example.pixelplax.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.ui.MovieAdapter
import com.example.pixelplax.databinding.FragmentFavoriteBinding
import com.example.pixelplax.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

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

            favoriteViewModel.favorite.observe(viewLifecycleOwner) { favorite ->
                movieAdapter.setData(favorite)
                binding.viewEmpty.root.visibility =
                    if (favorite.isNotEmpty()) View.GONE else View.VISIBLE
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}