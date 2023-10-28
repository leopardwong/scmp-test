package com.example.scmptest.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scmptest.api.model.LoginRequest
import com.example.scmptest.api.repository.ApiRepository.login
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginPageViewModel:ViewModel() {

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex("[a-zA-Z0-9]{6,10}"))
    }
    fun loginValid(email:String,
                   password: String,
                   token:(String)->Unit,
                   loading:(Boolean)->Unit,
                   error:(Boolean)->Unit){
        viewModelScope.launch {
            login(LoginRequest(email,password))
                .collectLatest {
                    it.isLoading?.let{
                        loading(it)
                    }
                    it.isSuccess?.let {
                        token(it.token)
                    }
                    it.isBizError?.let{
                        error(true)
                        Log.d("Error", "BizError: $it")
                    }
                    it.isOtherError?.let{
                        error(true)
                        Log.d("Error", "Throwable: $it")
                    }
                }
        }
    }
}