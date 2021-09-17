package com.jess.data.di

import com.jess.data.repository.ProductRepositoryImpl
import com.jess.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ProductRepository> {
        ProductRepositoryImpl(
            productsCacheDataSource = get(),
            productRemoteDataSource = get()
        )
    }
}
val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)