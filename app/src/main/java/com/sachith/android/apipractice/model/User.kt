package com.sachith.android.apipractice.model

data class User(
    val id: Number,
    val name: String,
    val username: String,
    val email: String,
    var addressStreet: String,
    var addressSuite: String,
    var addressCity: String,
    var addressZipcode: String,
)