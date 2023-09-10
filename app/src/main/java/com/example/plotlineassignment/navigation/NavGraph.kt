package com.example.plotlineassignment.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.plotlineassignment.ui.screens.TooltipConfigScreen
import com.example.plotlineassignment.ui.screens.TooltipRenderScreen
import com.example.plotlineassignment.viewmodel.TooltipViewModel

@Composable
fun TooltipsNavGraph(navController: NavHostController, viewmodel: TooltipViewModel) {
    NavHost(
        navController = navController,
        startDestination = Destinations.TooltipConfigScreen
    ) {
        composable(
            route = Destinations.TooltipConfigScreen,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(200)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(200)
                )
            },
        ) {
            TooltipConfigScreen(
                viewModel = viewmodel,
                navController = navController
            )
        }
        composable(route = Destinations.TooltipRenderScreen,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(200)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(200)
                )
            }
        ) {
            TooltipRenderScreen(viewModel = viewmodel, navController)
        }
    }
}