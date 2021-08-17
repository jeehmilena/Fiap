package com.jess.ondeeh.data.remote

import com.jess.ondeeh.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("/ws/{cep}/json/")
    fun searchAddress(@Path(" cep") cep: String): Call<Address>
}