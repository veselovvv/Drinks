package com.veselovvv.drinks.presentation.cocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Retry

class CocktailsFragment : Fragment() {
    private val viewModel: CocktailsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView // TODO add Toolbar?
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cocktails, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DrinksAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchCocktails()
            }
        )

        swipeToRefreshLayout = view.findViewById(R.id.cocktails_swipe_to_refresh)
        swipeToRefreshLayout.setOnRefreshListener {
            refreshUi(adapter)
            swipeToRefreshLayout.isRefreshing = false
        }

        recyclerView = view.findViewById(R.id.cocktails_recycler_view)
        recyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        refreshUi(adapter)
    }

    private fun refreshUi(adapter: DrinksAdapter) {
        viewModel.observe(this, { adapter.update(it) })
        viewModel.fetchCocktails()
    }
}