package com.example.scmptest.api.model

import com.google.gson.annotations.SerializedName

data class StaffsResponse(
    @SerializedName("page" ) var page : String,
    @SerializedName("per_page" ) var perPage : String,
    @SerializedName("total" ) var total : String,
    @SerializedName("total_pages" ) var totalPages : String,
    @SerializedName("data" ) var data : List<Staff>,
)

data class Staff(
    @SerializedName("id" ) var id : Int,
    @SerializedName("email" ) var email : String,
    @SerializedName("first_name" ) var firstName : String,
    @SerializedName("last_name" ) var lastName : String,
    @SerializedName("avatar" ) var avatar : String,
)
