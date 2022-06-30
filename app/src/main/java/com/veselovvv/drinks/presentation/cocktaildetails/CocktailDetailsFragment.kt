package com.veselovvv.drinks.presentation.cocktaildetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.R
import com.veselovvv.drinks.core.Retry
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class CocktailDetailsFragment : Fragment() {
    private val viewModel: CocktailDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cocktail_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cocktailName = viewModel.getCocktailName()
        view.findViewById<MaterialTextView>(R.id.cocktail_details_name).text = cocktailName
        view.findViewById<MaterialTextView>(R.id.cocktail_details_category).text =
            viewModel.getCocktailCategory()
        val photoImageView = view.findViewById<CircleImageView>(R.id.cocktail_details_photo)
        Glide.with(view).load(viewModel.getCocktailPhotoUrl()).into(photoImageView)

        viewModel.observe(this) { ui ->
            ui.map(view.findViewById(R.id.cocktail_details_progress_layout))
            ui.map(
                view.findViewById(R.id.cocktail_details_alcoholic),
                view.findViewById(R.id.cocktail_details_glass),
                view.findViewById(R.id.cocktail_details_instructions),
                listOf(
                    view.findViewById(R.id.cocktail_details_ingredient1),
                    view.findViewById(R.id.cocktail_details_ingredient2),
                    view.findViewById(R.id.cocktail_details_ingredient3),
                    view.findViewById(R.id.cocktail_details_ingredient4),
                    view.findViewById(R.id.cocktail_details_ingredient5),
                    view.findViewById(R.id.cocktail_details_ingredient6),
                    view.findViewById(R.id.cocktail_details_ingredient7),
                    view.findViewById(R.id.cocktail_details_ingredient8),
                    view.findViewById(R.id.cocktail_details_ingredient9),
                    view.findViewById(R.id.cocktail_details_ingredient10)
                )
            )
            ui.map(
                view.findViewById(R.id.cocktail_details_fail_layout),
                view.findViewById(R.id.fail_message_text_view),
                view.findViewById(R.id.fail_try_again_button),
                object : Retry {
                    override fun tryAgain() = viewModel.fetchCocktailDetails(cocktailName)
                }
            )
        }
        viewModel.fetchCocktailDetails(cocktailName)
    }
}