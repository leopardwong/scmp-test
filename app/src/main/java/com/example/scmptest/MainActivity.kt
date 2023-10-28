package com.example.scmptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scmptest.ui.NavigationGraph
import com.example.scmptest.ui.page.LoginPage
import com.example.scmptest.ui.viewModel.LoginPageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationGraph()
        }
    }
}

