package com.dev.android.dbitsdk

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(val string: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("User-Agent", string)
                .build()
        )
    }
}