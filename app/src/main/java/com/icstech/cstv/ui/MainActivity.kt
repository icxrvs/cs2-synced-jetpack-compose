package com.icstech.cstv.ui

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
import com.icstech.cstv.ui.main_screen.MainScreen
import com.icstech.cstv.ui.main_screen.MainViewModel
import com.icstech.cstv.ui.match_detail.MatchDetailScreen
import com.icstech.cstv.ui.match_detail.MatchDetailViewModel
import com.icstech.cstv.util.theme.CSTVTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val matchDetailViewModel: MatchDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CSTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "mainScreen") {
                        composable("mainScreen") { MainScreen(mainViewModel, navController) }
                        composable("matchDetailScreen/{matchId}") { backStackEntry ->
                            val myData = backStackEntry.arguments?.getString("matchId")
                            MatchDetailScreen(matchDetailViewModel,myData,navController)
                        }
                    }
                }
            }
        }
    }
}


