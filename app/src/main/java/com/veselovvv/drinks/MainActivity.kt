package com.veselovvv.drinks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragment_container_view)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.cocktailsFragment,
            R.id.categoriesFragment,
            R.id.randomCocktailFragment,
            R.id.searchIngredientFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)
    }
}