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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Retry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsFragment : Fragment() {
    private val viewModel: CocktailsViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cocktails, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DrinksAdapter(object : Retry {
                override fun tryAgain() = viewModel.fetchCocktails()
            }
        )

        toolbar = view.findViewById(R.id.cocktails_toolbar)
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

        val swipeToRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.cocktails_swipe_to_refresh)
        swipeToRefreshLayout.setOnRefreshListener {
            refreshUi(adapter)
            swipeToRefreshLayout.isRefreshing = false
        }

        view.findViewById<RecyclerView>(R.id.cocktails_recycler_view).apply {
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

    private fun refreshUi(adapter: DrinksAdapter) {
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