package com.veselovvv.drinks.presentation.searchingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import com.veselovvv.drinks.R
import com.veselovvv.drinks.databinding.FragmentSearchIngredientBinding
import com.veselovvv.drinks.presentation.core.BaseFragment
import com.veselovvv.drinks.presentation.core.SearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchIngredientFragment : BaseFragment<FragmentSearchIngredientBinding>() {
    private val viewModel: IngredientViewModel by viewModels()
    private lateinit var toolbar: Toolbar

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchIngredientBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = binding.searchIngredientToolbar
        with(toolbar) {
            inflateMenu(R.menu.search_ingredient_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_search -> {
                        (it.actionView as SearchView).apply {
                            queryHint = getString(R.string.search_an_ingredient)
                            setOnQueryTextListener(object : SearchListener() {
                                override fun find(query: String?): Boolean {
                                    viewModel.fetchIngredient(query.toString())
                                    return !query.isNullOrEmpty()
                                }
                                override fun onQueryTextChange(newText: String?) = true
                            })
                        }
                        true
                    } else -> false
                }
            }
        }

        viewModel.observe(this) { ui ->
            ui.map(binding.searchIngredientProgressLayout.root)
            ui.mapNoResults(binding.searchIngredientNoResultsLayout)
            ui.map(
                binding.searchIngredientName,
                binding.searchIngredientDescription,
                binding.searchIngredientType,
                binding.searchIngredientAlcohol,
                binding.searchIngredientAbv
            )
            ui.map(
                binding.searchIngredientFailLayout.root,
                binding.searchIngredientFailLayout.failMessageTextView,
                binding.searchIngredientFailLayout.failTryAgainButton
            )
        }
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.search_ingredient_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}