package com.example.lembretedecompras.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lembretedecompras.dao.ProductRoomDataBase
import com.example.lembretedecompras.models.Product
import com.example.lembretedecompras.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository
    val products: LiveData<List<Product>>

    init {
        val produtoDao = ProductRoomDataBase.getDatabase(application).productDao()
        repository = ProductRepository(produtoDao)
        products = repository.products
    }

    fun insert(product: Product) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(product)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        repository.delete()
    }

}