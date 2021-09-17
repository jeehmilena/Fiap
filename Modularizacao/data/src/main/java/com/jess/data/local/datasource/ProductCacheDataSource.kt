package com.jess.data.local.datasource

import com.jess.domain.model.Product
import io.reactivex.Single

interface ProductCacheDataSource {

    fun getProducts() : Single<List<Product>>

    fun insertData(products: List<Product>)

    fun updateData(products: List<Product>)

}