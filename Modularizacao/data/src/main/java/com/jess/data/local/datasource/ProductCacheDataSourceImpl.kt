package com.jess.data.local.datasource

import com.jess.data.local.database.ProductsDao
import com.jess.data.local.mapper.ProductCacheMapper
import com.jess.domain.model.Product
import io.reactivex.Single

class ProductCacheDataSourceImpl (
    private val productDao: ProductsDao
) : ProductCacheDataSource {

    override fun getProducts(): Single<List<Product>> {
        return productDao.getProducts().map { ProductCacheMapper.map(it) }
    }

    override fun insertData(products: List<Product>) {
        productDao.insertAll(ProductCacheMapper.mapProductToProductCache(products))
    }

    override fun updateData(products: List<Product>) {
        productDao.updateData(ProductCacheMapper.mapProductToProductCache(products))
    }
}