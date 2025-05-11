package com.downormal.moviesums.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
@Composable
fun App() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Route.Genres
    ) {
        composable <Route.Genres> {

        }
        composable <Route.Movies> {

        }
        composable <Route.MovieDetail> { backStackEntry ->

        }
    }

}