package com.jess.ondeeh.model

import kotlinx.serialization.SerialName

data class Address(
    @SerialName("bairro")
    val bairro: String = "",
    @SerialName("cep")
    val cep: String = "",
    @SerialName("complemento")
    val complemento: String = "",
    @SerialName("ddd")
    val ddd: String = "",
    @SerialName("gia")
    val gia: String = "",
    @SerialName("ibge")
    val ibge: String = "",
    @SerialName("localidade")
    val localidade: String = "",
    @SerialName("logradouro")
    val logradouro: String = "",
    @SerialName("siafi")
    val siafi: String = "",
    @SerialName("uf")
    val uf: String = ""
)