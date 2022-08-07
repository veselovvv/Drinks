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
            .backQueue.last().arguments?.getString("category") ?: ""

        val adapter = SubcategoriesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchSubcategories(categoryKey)
        },
        object : SubcategoriesAdapter.SubcategoryListener {
            override fun showSubcategory(subcategory: String) {
                // TODO save?
                requireActivity().findNavController(R.id.fragment_container_view)
                    .navigate(R.id.categoriesFragment) // TODO replace with right fragment
            }
        })

        val swipeToRefreshLayout = binding.subcategoriesSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchSubcategories(categoryKey) // TODO from network?
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