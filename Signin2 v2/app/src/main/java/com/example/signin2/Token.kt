package com.example.signin2

import android.media.session.MediaSession.Token
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Token(obj: LoginViewModel = viewModel()) {

   var token by remember { mutableStateOf("") }

    val response by obj.tokenResponse.collectAsState()
    Column {

        OutlinedTextField(
            value = token,
            onValueChange = { token = it },
            label = { Text(text = "Enter your Token") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(onClick = { obj.tokennn(token) }) {
            Text("Authorize")
        }

        if(response == true){
            Text(text = "Successfully Verfied!!!!!!!!",modifier = Modifier.background(Color.Green))
        }
        else
        {
            Text(text = "Something went wrong",modifier = Modifier.background(Color.Red))
        }

    }
}