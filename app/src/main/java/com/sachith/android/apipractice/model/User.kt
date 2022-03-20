package com.sachith.android.apipractice.model

data class User(
    val id: Number,
    val name: String,
    val username: String,
    val email: String,
    val address: UserAddress,
)