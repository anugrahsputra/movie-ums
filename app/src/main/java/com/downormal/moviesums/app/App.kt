package com.downormal.moviesums.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.downormal.moviesums.features.genres.presentation.GenresViewRoot
import com.downormal.moviesums.features.movies.presentation.MoviesViewRoot

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Genres,
    ) {
        composable <Route.Genres> {
            GenresViewRoot(
                navController = navController
            )

        }
        composable <Route.Movies> {
            MoviesViewRoot()
        }
        composable <Route.MovieDetail> { backStackEntry ->

        }
    }

}