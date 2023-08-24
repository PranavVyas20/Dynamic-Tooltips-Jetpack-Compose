package com.example.plotlineassignment.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plotlineassignment.Destinations
import com.example.plotlineassignment.ui.viewmodels.TooltipViewModel
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
                        composable(route = Destinations.TooltipConfigScreen) {
                            TooltipConfigScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(route = Destinations.TooltipRenderScreen) {
                            TooltipRenderScreen(viewModel = viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}
