package com.example.loginuser

import Login
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capitalone.ui.theme.CapitalOneTheme
import com.example.capitalone.views.configuration
import com.example.loginuser.data.RetrofitServiceFactory
import kotlinx.coroutines.launch
import upcoming_invoices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrofit Information Recopilation
        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {

            val resultado = service.getApiInfo()

            println(resultado)
        }

        enableEdgeToEdge()

        setContent {
            CapitalOneTheme {
                PreviewView()

            }
        }
    }
}


@Composable
fun PreviewView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            Login()
        }
        composable("upcoming_invoices") {
            upcoming_invoices()
        }
        composable("configuration") {
            configuration()
            /*
            configuration(
                navController = navController,
                userService = userService,
            )
            */

        }
    }
}


