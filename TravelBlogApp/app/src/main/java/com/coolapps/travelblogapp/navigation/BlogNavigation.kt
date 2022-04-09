package com.coolapps.travelblogapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coolapps.travelblogapp.screen.details.DetailScreen
import com.coolapps.travelblogapp.screen.home.HomeScreen
import com.coolapps.travelblogapp.screen.home.HomeScreenViewModel
import com.coolapps.travelblogapp.screen.login.BlogLoginScreen


@ExperimentalComposeUiApi
@Composable
fun BlogNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BlogScreen.LoginScreen.name
    ) {
        composable(BlogScreen.LoginScreen.name) {
            BlogLoginScreen(navController = navController)

        }
        composable(BlogScreen.BlogHomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController = navController,viewModel = homeViewModel)
        }
        val detailName = BlogScreen.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                DetailScreen(navController = navController)
            }

        }
    }
}