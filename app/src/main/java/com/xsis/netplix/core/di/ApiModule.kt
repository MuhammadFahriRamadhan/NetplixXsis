package com.xsis.netplix.core.di

import com.xsis.netplix.core.data.remote.ApiService
import retrofit2.Retrofit

class ApiModule {
    companion object {
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}