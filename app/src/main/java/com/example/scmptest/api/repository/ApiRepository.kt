package com.example.scmptest.api.repository

import com.example.scmptest.api.ApiState
import com.example.scmptest.api.ApiService
import com.example.scmptest.api.client.ApiClient
import com.example.scmptest.api.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

object ApiRepository {
    val apiService:ApiService = ApiClient.createRetrofit().create(ApiService::class.java)

    suspend fun login(request: LoginRequest) = flow {
        val result = apiService.login(5,request)
        emit(ApiState(isSuccess = result))
    }.flowOn(Dispatchers.IO).onStart {
        emit(ApiState(isLoading = true))
    }.catch {
        emit(ApiState(isOtherError = it))
    }

    suspend fun getStaffList(page:Int, token: String) = flow {
        val result = apiService.getStaff(page,token)
        emit(ApiState(isSuccess = result))
    }.flowOn(Dispatchers.IO).onStart {
        emit(ApiState(isLoading = true))
    }.catch {
        emit(ApiState(isOtherError = it))
    }

}