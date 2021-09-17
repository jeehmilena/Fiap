package com.jess.data.repository

import com.jess.data.local.datasource.ProductCacheDataSource
import com.jess.data.remote.datasource.ProductRemoteDataSource
import com.jess.domain.model.Product
import com.jess.domain.repository.ProductRepository
import io.reactivex.Single

class ProductRepositoryImpl (
    private val productsCacheDataSource: ProductCacheDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
): ProductRepository {
    override fun getProducts(forceUpdate: Boolean): Single<List<Product>> {
        return if (forceUpdate)
            getProductsRemote(forceUpdate)
        else
            productsCacheDataSource.getProducts()
                .flatMap { listJobs ->
                    when {
                        listJobs.isEmpty() -> getProductsRemote(false)
                        else -> Single.just(listJobs)
                    }
                }
    }

    private fun getProductsRemote(isUpdate: Boolean): Single<List<Product>> {
        return productRemoteDataSource.getProducts()
            .flatMap { products ->
                if (isUpdate)
                    productsCacheDataSource.updateData(products)
                else
                    productsCacheDataSource.insertData(products)
                Single.just(products)
            }
    }
}