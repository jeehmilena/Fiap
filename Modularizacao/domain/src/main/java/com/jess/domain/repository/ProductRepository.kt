package com.jess.domain.repository

import com.jess.domain.model.Product
import io.reactivex.Single

interface ProductRepository {
    fun getProducts(forceUpdate: Boolean):
            Single<List<Product>>
}