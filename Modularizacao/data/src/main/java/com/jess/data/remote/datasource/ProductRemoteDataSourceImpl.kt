package com.jess.data.remote.datasource

import com.jess.data.remote.api.ProductAPI
import com.jess.data.remote.mapper.ProductPayloadMapper
import com.jess.domain.model.Product
import io.reactivex.Single

class ProductRemoteDataSourceImpl(private val productAPI: ProductAPI) : ProductRemoteDataSource {

    override fun getProducts(): Single<List<Product>> {
        return productAPI.getProducts().map { ProductPayloadMapper.map(it) }
    }
}