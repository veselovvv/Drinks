package com.veselovvv.drinks.presentation.randomcocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FragmentCocktailDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomCocktailFragment : Fragment() {
    private var _binding: FragmentCocktailDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RandomCocktailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO fix dry here and further by creating a base fragment for this one and cocktailDetails
        val failLayout = binding.cocktailDetailsFailLayout
        val swipeToRefreshLayout = binding.cocktailDetailsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchRandomCocktail()
            swipeToRefreshLayout.isRefreshing = false
        }

        viewModel.observe(this) { ui ->
            ui.map(binding.cocktailDetailsProgressLayout.root)
            ui.map(
                view,
                binding.cocktailDetailsName,
                binding.cocktailDetailsCategory,
                binding.cocktailDetailsAlcoholic,
                binding.cocktailDetailsGlass,
                binding.cocktailDetailsInstructions,
                binding.cocktailDetailsPhoto,
                listOf(
                    binding.cocktailDetailsIngredient1,
                    binding.cocktailDetailsIngredient2,
                    binding.cocktailDetailsIngredient3,
                    binding.cocktailDetailsIngredient4,
                    binding.cocktailDetailsIngredient5,
                    binding.cocktailDetailsIngredient6,
                    binding.cocktailDetailsIngredient7,
                    binding.cocktailDetailsIngredient8,
                    binding.cocktailDetailsIngredient9,
                    binding.cocktailDetailsIngredient10
                )
            )
            ui.map(
                failLayout.root,
                failLayout.failMessageTextView,
                failLayout.failTryAgainButton,
                object : Retry {
                    override fun tryAgain() = viewModel.fetchRandomCocktail()
                }
            )
        }
        viewModel.fetchRandomCocktail()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}