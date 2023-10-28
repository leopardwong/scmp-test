package com.example.scmptest.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scmptest.api.model.LoginRequest
import com.example.scmptest.api.repository.ApiRepository.getUserList
import com.example.scmptest.api.repository.ApiRepository.login
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginPageViewModel:ViewModel() {

    fun getApi(){
        viewModelScope.launch {
            login(LoginRequest("eve.holt@reqres.in","cityslicka"))
                .map { response ->
                    response.isSuccess?.let {
                        it.token
                    }
                }
                .flatMapConcat { token ->
                    println("token======$token")
                    getUserList(token?:"")
                }
                .collect {
                    it.isSuccess?.let {
                        println("isSuccess======$it")
                    }
                    it.isBizError?.let{
                        println("isBizError======$it")
                    }
                    it.isOtherError?.let{

                        println("isOtherError======$it")
                    }
                }
        }
    }
}