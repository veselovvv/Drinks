package com.veselovvv.drinks.presentation.subcategorycocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.drinks.R
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

        // TODO here and in other fragments - fix DRY: (make function and add to BaseFragment)
        val categoryKey = requireActivity().findNavController(R.id.fragment_container_view)
            // TODO here and in all other places like that - move "categoryKey" to a constant
            .backQueue.last().arguments?.getString("categoryKey") ?: ""

        val subcategoryName = requireActivity().findNavController(R.id.fragment_container_view)
            .backQueue.last().arguments?.getString("subcategoryName") ?: ""

        val adapter = SubcategoryCocktailsAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchCocktails(categoryKey, subcategoryName)
        },
        object : SubcategoryCocktailsAdapter.CocktailListener {
            override fun showCocktail(name: String, photoUrl: String) {
                //TODO save cocktail info
                //TODO navigate to cocktail details fragment
            }
        })

        val swipeToRefreshLayout = binding.subcategoryCocktailsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchCocktails(categoryKey, subcategoryName)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.subcategoryCocktailsRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        viewModel.observe(this) { adapter.update(it) }
        viewModel.fetchCocktails(categoryKey, subcategoryName)
    }
}