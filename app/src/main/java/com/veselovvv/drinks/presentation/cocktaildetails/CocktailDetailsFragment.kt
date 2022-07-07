package com.veselovvv.drinks.presentation.cocktaildetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FragmentCocktailDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailsFragment : Fragment() {
    private var _binding: FragmentCocktailDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cocktailName = viewModel.getCocktailName()
        binding.cocktailDetailsName.text = cocktailName
        binding.cocktailDetailsCategory.text = viewModel.getCocktailCategory()
        Glide.with(view).load(viewModel.getCocktailPhotoUrl()).into(binding.cocktailDetailsPhoto)

        val cocktailDetailsFailLayout = binding.cocktailDetailsFailLayout

        val swipeToRefreshLayout = binding.cocktailDetailsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchCocktailDetailsFromNetwork(cocktailName)
            swipeToRefreshLayout.isRefreshing = false
        }

        viewModel.observe(this) { ui ->
            ui.map(binding.cocktailDetailsProgressLayout.root)
            ui.map(
                binding.cocktailDetailsAlcoholic,
                binding.cocktailDetailsGlass,
                binding.cocktailDetailsInstructions,
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
                cocktailDetailsFailLayout.root,
                cocktailDetailsFailLayout.failMessageTextView,
                cocktailDetailsFailLayout.failTryAgainButton,
                object : Retry {
                    override fun tryAgain() = viewModel.fetchCocktailDetails(cocktailName)
                }
            )
        }
        viewModel.fetchCocktailDetails(cocktailName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}