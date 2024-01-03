package com.example.signin2

    import android.util.Log
    import androidx.compose.runtime.MutableState
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch
    import retrofit2.Response
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory

    class LoginViewModel : ViewModel() {

        val api = Retrofit.Builder()
            .baseUrl("https://api.dev2.constructn.ai")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var apiservice = api.create(Apii::class.java)
        //sign-in
        private var _loginSuccess = MutableStateFlow(false)
        var loginSuccess: StateFlow<Boolean?> = _loginSuccess

        private var _loginResponse = MutableStateFlow<LoginResponse?>(null)
        var loginResponse : StateFlow<LoginResponse?> = _loginResponse

        //sign-up
        private var _signupSuccess = MutableStateFlow(false)
        var signupSuccess: StateFlow<Boolean?> = _signupSuccess

        private var _signupresponse = MutableStateFlow<RegisterResponse?>(null)
        var signupresponse: StateFlow<RegisterResponse?> = _signupresponse

        //token-response
        private var _tokenResponse = MutableStateFlow<Boolean?>(false)
        val tokenResponse : StateFlow<Boolean?> = _tokenResponse

        fun signIn(data: LoginCheck) {
            viewModelScope.launch {
                try {
                    Log.d("YourTag", "Signin clicked")

                    // Making the network request
                    val response: Response<LoginResponse> = apiservice.signin(data)

                    // Check if the response is successful
                    if (response.isSuccessful) {
                        //to store response.body to print in ui
                        _loginResponse.value = response.body()
                        _loginSuccess.value = true

                        // Print response body to logs
                        Log.d("YourTag", "Response body: ${response.body()}")

                        // Print sign-in data to logs
                        Log.d("YourTag", "Sign-in data: $data")

                        // Perform further actions with the response data if needed
                        // For example, update UI or navigate to another screen
                    } else {
                        // Handle unsuccessful response (e.g., server error, invalid credentials)
                        Log.e("YourTag", "Unsuccessful response: ${response.code()}")

                        _loginResponse.value = response.body()
                        _loginSuccess.value = false
                    }
                } catch (e: Exception) {
                    // Handle exceptions (e.g., network issues)
                    Log.e("YourTag", "Exception: ${e.message}", e)
                }
            }
        }

        fun register(data1: RegisterCheck) {
            viewModelScope.launch {
                try {
                    Log.d("YourTag", "Signin clicked")

                    // Making the network request
                    val response: Response<RegisterResponse> = apiservice.register(data1)

                    // Check if the response is successful
                    if (response.isSuccessful) {

                        //passing the data to the mutable state flow that can accessed from anywhere in the project.
                        _signupresponse.value = response.body()
                        _signupSuccess.value = true


                        // Print response body to logs
                        Log.d("YourTag", "Response body: ${response.body()}")

                        // Print sign-in data to logs
                        Log.d("YourTag", "Sign-in data: $data1")

                        // Perform further actions with the response data if needed
                        // For example, update UI or navigate to another screen
                    } else {
                        // Handle unsuccessful response (e.g., server error, invalid credentials)
                        Log.e("YourTag", "Unsuccessful response: ${response.code()}")
                    }
                } catch (e: Exception) {
                    // Handle exceptions (e.g., network issues)
                    Log.e("YourTag", "Exception: ${e.message}", e)
                }
            }
        }
        fun tokennn(token : String){
            viewModelScope.launch {

                val response : Response<TokenResponse> = apiservice.token(token)

                if(response.isSuccessful){

                    _tokenResponse.value = true

                    // Print response body to logs
                    Log.d("YourTag", "Response body: ${response.body()}")

                    // Print sign-in data to logs
                    Log.d("YourTag", "Sign-in data: $token")

                    // Perform further actions with the response data if needed
                    // For example, update UI or navigate to another screen
                }

            }
        }
    }