package com.dev.android.dbitsdk

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainRetrofitInstance() {
    companion object {
        var userAgent:String = ""
        var gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(HeaderInterceptor(userAgent))
                .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(1, TimeUnit.MINUTES) // write timeout
                .readTimeout(1, TimeUnit.MINUTES) //
                .build()
            Retrofit.Builder()
                .baseUrl("https://soccer.sportmonks.com/api/v2.0/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ServerAPI::class.java)
        }
    }
}