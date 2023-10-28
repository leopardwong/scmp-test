package com.example.scmptest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scmptest.ui.page.LoginPage
import com.example.scmptest.ui.page.StaffListPage

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login"){
        composable("Login"){ LoginPage(navController)}
        composable("staffList/{token}"){ backStackEntry ->
            val token = backStackEntry.arguments?.getString("token")
            StaffListPage(token = token?: "")
        }
    }
}