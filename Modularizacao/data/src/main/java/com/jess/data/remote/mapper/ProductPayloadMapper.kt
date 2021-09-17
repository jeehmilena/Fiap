package com.jess.data.remote.mapper

import com.jess.data.remote.model.ProductPayload
import com.jess.domain.model.Product

object ProductPayloadMapper {

    fun map(products: List<ProductPayload>) = products.map { map(it) }

    private fun map(productPayload: ProductPayload) = Product(
        name = productPayload.name,
        imageURL = productPayload.imageURL,
        description = productPayload.description
    )
}