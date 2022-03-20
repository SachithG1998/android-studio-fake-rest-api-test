package com.sachith.android.apipractice.api

// Importing models
import com.sachith.android.apipractice.model.User

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPIService {
    @GET("users/{userId}")
    fun getUser(@Path("userId") id: String): Call<User>

    companion object {
        val FAKE_API_URL = "https://jsonplaceholder.typicode.com"
        fun create(): UserAPIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FAKE_API_URL)
                .build()
            return retrofit.create(UserAPIService::class.java)
        }
    }
}