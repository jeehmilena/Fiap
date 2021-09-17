package com.jess.data.di

import com.jess.data.local.database.ProductDataBase
import com.jess.data.local.datasource.ProductCacheDataSource
import com.jess.data.local.datasource.ProductCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { ProductDataBase.createDataBase(androidContext()) }

    factory<ProductCacheDataSource> { ProductCacheDataSourceImpl(productDao = get())  }

}