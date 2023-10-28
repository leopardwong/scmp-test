package com.example.scmptest.api.model

import com.google.gson.annotations.SerializedName

data class StaffsResponse(
    @SerializedName("page" ) var page : Int,
    @SerializedName("per_page" ) var perPage : Int,
    @SerializedName("total" ) var total : Int,
    @SerializedName("total_pages" ) var totalPages : Int,
    @SerializedName("data" ) var data : List<Staff>,
)

data class Staff(
    @SerializedName("id" ) var id : Int,
    @SerializedName("email" ) var email : String,
    @SerializedName("first_name" ) var firstName : String,
    @SerializedName("last_name" ) var lastName : String,
    @SerializedName("avatar" ) var avatar : String,
)
