package com.veselovvv.drinks.presentation.cocktaildetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailsFragment : Fragment() {
    private val viewModel: CocktailDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cocktail_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialTextView>(R.id.cocktail_detail_text_view).text =
            "${viewModel.getCocktailName()} - ${viewModel.getCocktailCategory()}"
    }
}