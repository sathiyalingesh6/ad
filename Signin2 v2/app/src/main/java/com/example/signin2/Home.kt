package com.example.signin2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(data : LoginResponse?) {
    Text("Home")
   // val data  by obj.loginResponse.collectAsState()

    Column {
        Text(text = "Name : ${data?.result?.firstName}")
        Text(text = "Email : ${data?.result?.email}")
        Text(text = "Password : ${data?.result?.password}")
        Text(text = "Age : ${data?.result?.age}")
        Text(text = "token : ${data?.result?.token}")

    }
}