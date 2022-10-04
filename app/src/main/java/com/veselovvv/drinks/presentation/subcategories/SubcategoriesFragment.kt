package com.veselovvv.drinks.presentation.subcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Category
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

        val category = getArgument(CATEGORY) as Category

        val adapter = SubcategoriesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchSubcategories(category)
        },
        object : SubcategoriesAdapter.SubcategoryListener {
            override fun showSubcategory(subcategoryName: String) {
                val bundle = Bundle()
                bundle.apply {
                    putSerializable(CATEGORY, category)
                    putString(SUBCATEGORY_NAME, subcategoryName)
                }
                navigateWithArguments(R.id.subcategoryCocktailsFragment, bundle)
            }
        })

        val swipeToRefreshLayout = binding.subcategoriesSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchSubcategories(category)
            swipeToRefreshLayout.isRefreshing = false
        }

        binding.subcategoriesRecyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        viewModel.observe(this) { adapter.update(it) }
        viewModel.fetchSubcategories(category)
    }

    companion object {
        private const val CATEGORY = "category"
        private const val SUBCATEGORY_NAME = "subcategoryName"
    }
}