package com.example.signin2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FetchUserData(obj : LoginViewModel = viewModel()){

    var token by remember{ mutableStateOf("") }
    var buttonClicked by remember{ mutableStateOf(false)}
    val profileResponse1 by obj.profileSuccess.collectAsState()


    //To update Details
    val res by obj.profileresponse.collectAsState()

    var fname by remember{ mutableStateOf("${res?.result?.firstName}") }
    var lname by remember{ mutableStateOf("${res?.result?.lastName}") }
    var email by remember { mutableStateOf("${res?.result?.email}")}
    var id by remember { mutableStateOf("${res?.result?._id}") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ){
        OutlinedTextField(
            value = token,
            onValueChange = { token = it },
            label = { Text("Enter Profile Token") },
            singleLine = false,
        )

        Button(onClick = { obj.fetchUserProfile(token) }) {
            Text("Click to Fetch User Details")
            buttonClicked = true
        }




            if (buttonClicked && profileResponse1 == true) {

                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ), modifier = Modifier
                        .padding(10.dp)

                ) {
//            Icon(
//                painter = painterResource(R.drawable.ic_launcher_foreground),
//                contentDescription = null,
//                modifier = Modifier.clip(CircleShape).height(10.dp).width(10.dp)
//            )
                    Text(text = "User Details")
                    OutlinedTextField(
                        value = "${res?.result?._id}",
                        onValueChange = { id = it },
                        label = { Text(text = "First Name") },
                        singleLine = true,
                    )
                    OutlinedTextField(
                        value = "${res?.result?.firstName}",
                        onValueChange = { fname = it },
                        label = { Text(text = "First Name") },
                        singleLine = true,
                    )
                    OutlinedTextField(
                        value = "${res?.result?.lastName}",
                        onValueChange = { lname = it },
                        label = { Text(text = "Last Name") },
                        singleLine = true,
                    )

                    OutlinedTextField(
                        value = "${res?.result?.email}",
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        singleLine = true,
                    )

                    

                    Button(onClick = {  }) {
                        Text("Update Details")
                    }

                }
            }
        }
    }
