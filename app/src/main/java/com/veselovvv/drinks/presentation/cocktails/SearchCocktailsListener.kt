package com.veselovvv.drinks.presentation.cocktails

import androidx.appcompat.widget.SearchView

abstract class SearchCocktailsListener : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?) = find(query)
    override fun onQueryTextChange(newText: String?) = find(newText)

    abstract fun find(query: String?): Boolean
}