package com.example.capitalone.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capitalone.ui.theme.Purple80

@Preview
@Composable
fun upcoming_invoices(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "August 31")
        invoice()
    }
}

@Composable
fun invoice(){
    Row (
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .width(100.dp)
            .height(50.dp)
    ){
        Text("Hello WOrld")
        Text("$27.80")
    }
}
