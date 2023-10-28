package com.example.scmptest

import com.example.scmptest.api.ApiService
import com.example.scmptest.api.model.LoginRequest
import com.example.scmptest.api.model.LoginResponse
import com.example.scmptest.api.model.Staff
import com.example.scmptest.api.model.StaffsResponse
import com.example.scmptest.api.repository.ApiRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiServiceUnitTest {
    private val mockApiService: ApiService = mock(ApiService::class.java)
    private val apiRepository = ApiRepository

    @Test
    fun testLogin() = runBlocking {
        val loginRequest = LoginRequest("eve.holt@reqres.in", "cityslicka")
        val expectedLoginResponse = LoginResponse("QpwL5tke4Pnpja7X4")

        `when`(mockApiService.login(5, loginRequest)).thenReturn(expectedLoginResponse)

        var loginResponse: LoginResponse? = null

        apiRepository.login(loginRequest).collectLatest {
            loginResponse = it.isSuccess
        }
        assertEquals(expectedLoginResponse, loginResponse)
    }

    @Test
    fun testGetStaffList() = runBlocking {
        val page = 1
        val token = "QpwL5tke4Pnpja7X4"
        val data = listOf(
            Staff(1,"george.bluth@reqres.in","George","Bluth","https://reqres.in/img/faces/1-image.jpg"),
            Staff(2,"janet.weaver@reqres.in","Janet","Weaver","https://reqres.in/img/faces/2-image.jpg"),
            Staff(3,"emma.wong@reqres.in","Emma","Wong","https://reqres.in/img/faces/3-image.jpg"),
            Staff(4,"eve.holt@reqres.in","Eve","Holt","https://reqres.in/img/faces/4-image.jpg"),
            Staff(5,"charles.morris@reqres.in","Charles","Morris","https://reqres.in/img/faces/5-image.jpg"),
            Staff(6,"tracey.ramos@reqres.in","Tracey","Ramos","https://reqres.in/img/faces/6-image.jpg"),
            )

        val expectedStaffsResponse = StaffsResponse(page, 6, 12, 2, data)

        `when`(mockApiService.getStaff(page, token)).thenReturn(expectedStaffsResponse)

        var staffsResponse: StaffsResponse? = null

        apiRepository.getStaffList(page, token).collectLatest {
            staffsResponse = it.isSuccess
        }

        assertEquals(expectedStaffsResponse, staffsResponse)
    }
}