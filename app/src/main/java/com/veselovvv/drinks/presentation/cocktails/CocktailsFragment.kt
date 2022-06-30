package com.veselovvv.drinks.presentation.cocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FragmentCocktailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsFragment : Fragment() {
    private var _binding: FragmentCocktailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailsViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CocktailsAdapter(object : Retry {
                override fun tryAgain() = viewModel.fetchCocktails()
            },
            object : CocktailsAdapter.CocktailListener {
                override fun showCocktail(name: String, category: String, photoUrl: String) {
                    viewModel.saveCocktailInfo(name, category, photoUrl)
                    requireActivity().findNavController(R.id.fragment_container_view)
                        .navigate(R.id.cocktailDetailsFragment)
                }
            }
        )

        toolbar = binding.cocktailsToolbar
        with(toolbar) {
            title = getString(R.string.cocktails)
            inflateMenu(R.menu.cocktails_toolbar_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_search -> {
                        (it.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_cocktails)
                            setOnQueryTextListener(SearchCocktailsListener())
                        }
                        true
                    } else -> false
                }
            }
        }

        val swipeToRefreshLayout = binding.cocktailsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            refreshUi(adapter)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.cocktailsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        refreshUi(adapter)
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.cocktails_toolbar_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshUi(adapter: CocktailsAdapter) {
        viewModel.observe(this, { adapter.update(it) })
        viewModel.fetchCocktails()
    }

    private inner class SearchCocktailsListener : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?) = find(query)
        override fun onQueryTextChange(newText: String?) = find(newText)

        private fun find(query: String?): Boolean {
            viewModel.searchCocktails(query.toString())
            return !query.isNullOrEmpty()
        }
    }
}