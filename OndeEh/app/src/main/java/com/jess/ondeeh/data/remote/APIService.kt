package com.jess.ondeeh.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIService {
    private var INSTANCE: AddressService? = null
    val instance: AddressService?
        get() {
            if (INSTANCE == null) {
                val client = OkHttpClient.Builder().build()
                val retrofit = Retrofit.Builder().baseUrl("https://viacep.com.br")
                    .addConverterFactory(MoshiConverterFactory.create()).client(client)
                    .build()

                INSTANCE = retrofit.create(AddressService::class.java)
            }

            return INSTANCE
        }
}