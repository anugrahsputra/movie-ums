package com.downormal.moviesums.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.downormal.moviesums.features.genres.presentation.GenresViewRoot

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
@Composable
fun App() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Route.Genres,
    ) {
        composable <Route.Genres> {
            GenresViewRoot()

        }
        composable <Route.Movies> {

        }
        composable <Route.MovieDetail> { backStackEntry ->

        }
    }

}