package com.example.capitalone.views

import CustomBottomAppBar
import CustomTopAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.example.capitalone.ui.theme.CO_Blue
import com.example.capitalone.ui.theme.CO_Grey

@Composable
fun InvoiceBox(title: String, url: String){
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color = CO_Grey)
            .padding(8.dp)
    ) {
        Column(){
            Text(title)
            Text(url)
        }
    }
}

@Composable
fun NotificationBox(text: String){
    Box(modifier = Modifier
        .height(50.dp)
        .width(100.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(color = CO_Grey),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,

        )
    }
}

@Composable
fun configurationColumn(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ){
        // Información Personal
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),

        ){
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Lol",
                Modifier.size(90.dp)
            )
            Column(
                modifier = Modifier
                    .align( alignment = Alignment.CenterVertically)
            ) {
                Text(text = "Esteban Sierra Baccio",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center)
                Text(text = "e.s.baccio@gmail.com",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center)
            }
        }

        // Notification Settings
        Column(){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp) // Agrega el padding general
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f) // Ocupa todo el espacio disponible a la izquierda
                ) {
                    Text(
                        text = "Notification Settings",
                        fontSize = 20.sp
                    )
                    Text(text = "For you not to forget to make your invoices")
                }

                // Switch a la derecha
                var checked by remember { mutableStateOf(true) }
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ){
                NotificationBox("1 day")
                NotificationBox("2 days")
                NotificationBox("3 days")
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ){
                NotificationBox("1 week")
                NotificationBox("2 weeks")
                NotificationBox("3 weeks")
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        // Automatic Invoices
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // Agrega el padding general
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = "Automatic Invoices",
                    fontSize = 20.sp
                )
                Text(text="You don’t want to add them manaually, do you?")
            }
            var checked by remember { mutableStateOf(true) }
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }

        // Frequent Invoices
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // Agrega el padding general
        ){
            Row(){
                Column(
                    modifier = Modifier.weight(8f)
                ) {
                    Text(
                        text = "Frequent Invoices",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Manually create redirect invoices"
                    )
                }
                var checked by remember { mutableStateOf(true) }
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Column(){
                    InvoiceBox(title="Tacos Pancha", url="https://tacos.com")
                    Spacer(modifier = Modifier.height(8.dp))
                    InvoiceBox(title="Jochos pepe", url="https://pepe.com")
                }
                Column(){
                    InvoiceBox(title="Tacos Pancha", url="https://tacos.com")
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = CO_Blue),
                        contentAlignment = Alignment.Center
                    ) { Text(
                        modifier = Modifier.fillMaxWidth(),
                        text="+",
                        color = Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                    }
                }
            }




        }

        // About the APp
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // Agrega el padding general
        ){
            Text(text = "About the App",
                fontSize = 20.sp
            )
            Text(
                text = "Information about the app Information about the app Information about the app Information about the app Information about the app Information about the app Information about the appInformation about the app Information about the app Information about the app Information about the app Information about the app Information about the app Information about the app Information about the appInformation about the app"
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