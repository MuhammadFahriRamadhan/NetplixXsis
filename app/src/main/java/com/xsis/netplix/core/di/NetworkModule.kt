package com.xsis.netplix.core.di

import android.util.Log
import com.xsis.netplix.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {
    companion object {
        private const val BASE_URL= "https://api.themoviedb.org/3/"
        private const val API_KEY = BuildConfig.API_KEY

        fun provideRetrofitNetplixXsisService(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(apiKeyInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        fun httpInterceptor() = HttpLoggingInterceptor().apply {
            return HttpLoggingInterceptor { message ->
                // Error message: <-- HTTP FAILED: java.net.SocketTimeoutException: timeout
                Log.d("TAG",message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        // Interceptor to add API Key to all requests
        private fun apiKeyInterceptor() = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            // Modify the URL to add the API key as a query parameter
            val urlWithApiKey = originalUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            // Build the new request with the modified URL
            val newRequest = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()

            chain.proceed(newRequest)
        }
    }
}