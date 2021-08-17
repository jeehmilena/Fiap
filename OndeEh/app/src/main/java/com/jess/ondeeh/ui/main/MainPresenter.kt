package com.jess.ondeeh.ui.main

import com.jess.ondeeh.data.remote.APIService
import com.jess.ondeeh.model.Address
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var view: MainContract.MainView) :
    MainContract.MainPresenter {

    override fun search(cep: String) {
        if (cep.isEmpty()) {
            view.showError("Informe o CEP")
        } else {
            APIService.instance
                ?.searchAddress(cep)
                ?.enqueue(object : Callback<Address> {
                    override fun onResponse(
                        call: Call<Address>,
                        response: Response<Address>
                    ) {
                        if (response.isSuccessful) {
                            view.showAddress(response.body())
                        } else {
                            view.showError(
                                "Endereço não encontrado ")
                        }
                    }

                    override fun onFailure(
                        call: Call<Address>,
                        t: Throwable
                    ) {
                        view.showError(t.message.toString())
                    }
                })

        }
    }
}