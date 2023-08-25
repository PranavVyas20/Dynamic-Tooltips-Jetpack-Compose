package com.example.plotlineassignment.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plotlineassignment.navigation.Destinations
import com.example.plotlineassignment.viewmodel.TooltipViewModel
import com.example.plotlineassignment.ui.screens.TooltipConfigScreen
import com.example.plotlineassignment.ui.screens.TooltipRenderScreen
import com.example.plotlineassignment.ui.theme.PlotlineAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: TooltipViewModel by viewModels()
        setContent {
            PlotlineAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.TooltipConfigScreen
                    ) {
                        composable(
                            route = Destinations.TooltipConfigScreen,
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                                    animationSpec = tween(200)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                                    animationSpec = tween(200)
                                )
                            },
                        ) {
                            TooltipConfigScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(route = Destinations.TooltipRenderScreen,
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
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
                            TooltipRenderScreen(viewModel = viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}
