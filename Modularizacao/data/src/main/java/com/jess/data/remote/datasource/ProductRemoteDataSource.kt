package com.jess.data.remote.datasource

import com.jess.domain.model.Product
import io.reactivex.Single

interface ProductRemoteDataSource {
    fun getProducts() : Single<List<Product>>
}