package com.example.lembretedecompras.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lembretedecompras.models.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductRoomDataBase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ProductRoomDataBase? = null

        fun getDatabase(context: Context): ProductRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductRoomDataBase::class.java,
                    "lembrete_compra_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}