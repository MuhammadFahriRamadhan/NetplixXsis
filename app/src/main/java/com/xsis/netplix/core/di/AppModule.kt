package com.xsis.netplix.core.di

import com.xsis.netplix.core.data.repositoryimpl.MovieRepositoryImpl
import com.xsis.netplix.core.domain.repository.MovieRepository
import com.xsis.netplix.view.main.MainViewModel
import com.xsis.netplix.view.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { ApiModule.provideApiService(get()) }
}

val networkModule = module {
    single { NetworkModule.provideOkhttpClient(get()) }
    single { NetworkModule.provideRetrofitNetplixXsisService(get()) }
    single { NetworkModule.httpInterceptor() }
}


 val repoModule = module {
     single <MovieRepository> {  MovieRepositoryImpl(get()) }
 }

val viewModel = module {
    viewModel { MainViewModel() }
    viewModel { SearchViewModel() }
}

