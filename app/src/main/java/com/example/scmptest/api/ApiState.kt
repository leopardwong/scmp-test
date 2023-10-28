package com.example.scmptest.api

open class ApiState<T>(
    val isLoading: Boolean = false,
    val isSuccess: T? = null,
    val isBizError: Error? = null,
    val isOtherError: Throwable? = null
)