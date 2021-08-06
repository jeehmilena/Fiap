package com.example.lembretedecompras.repository

import androidx.lifecycle.LiveData
import com.example.lembretedecompras.dao.ProductDao
import com.example.lembretedecompras.models.Product

class ProductRepository(private val productDao: ProductDao) {

    val products: LiveData<List<Product>> = productDao.getListProducts()

    suspend fun insert(product: Product){
        productDao.insert(product)
    }

    suspend fun delete(){
        productDao.deleteAll()
    }

    suspend fun deleteProduct(product: Product){
        productDao.deleteProduct(product)
    }
}