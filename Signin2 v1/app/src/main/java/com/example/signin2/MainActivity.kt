package com.example.signin2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signin2.ui.theme.Signin2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Signin2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun Signin(navController: NavController, obje :LoginViewModel = viewModel() ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }



    var isLoginButtonClicked by remember { mutableStateOf(false) }
    val loginSuccess by obje.loginSuccess.collectAsState()
    Column {


        //email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        //password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter your Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        //signin button
        Button(onClick = {
            val details = LoginCheck(email = email, password = password)
            println("Login Clicked")
            obje.signIn(details)
            isLoginButtonClicked = true


        }) {
            Text("Login")
        }

        if(loginSuccess == true && isLoginButtonClicked) {
            println("Login Success")
            println(obje.loginSuccess)
            navController.navigate("Home")
        }
        //Register button
        Button(onClick = { navController.navigate("SignUp") }) {
            Text("Register")
        }
    }

    }

@Composable
fun App() {
    var navcontroller = rememberNavController()
    var obj : LoginViewModel = viewModel()
    NavHost(navController = navcontroller, startDestination = "SignIn") {
        composable("SignIn"){
            Signin(navcontroller, obj )
        }
        composable("SignUp"){
            Signup(navcontroller,obj)
        }
        composable("Token")
        {
            Token(obj)
        }
        composable("Home"){
            val data  by obj.loginResponse.collectAsState()

            Home(data)
        }

    }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Signin2Theme {
//        //Greeting("Android")
////        App()
//    }
//}

