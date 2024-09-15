package com.example.capitalone.views

import CustomBottomAppBar
import CustomTopAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun configurationColumn(){
    Column(){
        // Información Personal
        Row(
            modifier = Modifier.height(100.dp)
        ){
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Lol",
                Modifier.size(90.dp)
            )
            Column {
                Text(text = "Esteban Sierra Baccio")
                Text(text = "e.s.baccio@gmail.com")
            }
        }

        // Notification Settings
        Row(){
            Text(
                text ="Notification Settings",
                fontSize = 20.sp
            )
        }

        // Automatic Invoices
        Row(){
            Text(
                text = "Automatic Invoices",
                fontSize = 20.sp
            )
        }

        // Frequent Invoices
        Row(){
            Text(
                text = "Frequent Invoices",
                fontSize = 20.sp
            )
        }

        // About the APp
        Column(){
            Text(text = "About the App",
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun configuration(){
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Configuration")
        },
        bottomBar = {
            CustomBottomAppBar(page = 1)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            configurationColumn()
        }
    }
}