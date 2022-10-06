package com.veselovvv.drinks.presentation.subcategorycocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FragmentSubcategoryCocktailsBinding
import com.veselovvv.drinks.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubcategoryCocktailsFragment : BaseFragment<FragmentSubcategoryCocktailsBinding>() {
    private val viewModel: SubcategoryCocktailsViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSubcategoryCocktailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = getArgument(CATEGORY) as Category
        val subcategoryName = getStringArgument(SUBCATEGORY_NAME)

        val adapter = SubcategoryCocktailsAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchCocktails(category, subcategoryName)
        },
        object : SubcategoryCocktailsAdapter.CocktailListener {
            override fun showCocktail(name: String, photoUrl: String) {
                viewModel.saveCocktailInfo(name, subcategoryName, photoUrl)
                navigate(R.id.cocktailDetailsFragment)
            }
        })

        val swipeToRefreshLayout = binding.subcategoryCocktailsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchCocktails(category, subcategoryName)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.subcategoryCocktailsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        viewModel.observe(this) { adapter.update(it) }
        viewModel.fetchCocktails(category, subcategoryName)
    }

    companion object {
        private const val CATEGORY = "category"
        private const val SUBCATEGORY_NAME = "subcategoryName"
    }
}