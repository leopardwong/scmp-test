package com.example.scmptest.api.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token" ) var token : String,
)
