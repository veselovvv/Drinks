package com.veselovvv.drinks.presentation.subcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FragmentSubcategoriesBinding
import com.veselovvv.drinks.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubcategoriesFragment : BaseFragment<FragmentSubcategoriesBinding>() {
    private val viewModel: SubcategoriesViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSubcategoriesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryKey = requireActivity().findNavController(R.id.fragment_container_view)
            .backQueue.last().arguments?.getString("category") ?: "" // TODO here and further replace with "categoryKey"

        val adapter = SubcategoriesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchSubcategories(categoryKey)
        },
        object : SubcategoriesAdapter.SubcategoryListener {
            override fun showSubcategory(subcategory: String) {
                val bundle = Bundle()
                bundle.apply {
                    putString("categoryKey", categoryKey)
                    putString("subcategoryName", subcategory)
                }
                requireActivity().findNavController(R.id.fragment_container_view)
                    .navigate(R.id.subcategoryCocktailsFragment, bundle)
            }
        })

        val swipeToRefreshLayout = binding.subcategoriesSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchSubcategories(categoryKey)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.subcategoriesRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        viewModel.observe(this) { adapter.update(it) }
        viewModel.fetchSubcategories(categoryKey)
    }
}