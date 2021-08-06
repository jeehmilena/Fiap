package com.example.lembretedecompras.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lembretedecompras.models.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("SELECT * FROM product ORDER BY product_name ASC")
    fun getListProducts(): LiveData<List<Product>>

    @Query("DELETE FROM product")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteProduct(product: Product)
}