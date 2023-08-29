package com.dev.android.dbitsdk


import retrofit2.Call
import retrofit2.http.GET


interface ServerAPI {

    @GET("sdktest")
    fun serverStatus(): Call<ServerStatus>
}