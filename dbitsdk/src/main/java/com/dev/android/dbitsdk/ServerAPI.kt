package com.dev.android.dbitsdk


import retrofit2.Call
import retrofit2.http.GET


interface ServerAPI {

    @GET("football_odds")
    fun serverStatus(): Call<ServerStatus>
}