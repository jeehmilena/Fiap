package com.jess.ondeeh.ui.main

import com.jess.ondeeh.model.Address

interface MainContract {
    interface MainView {
        fun showAddress(address: Address?)
        fun showError(message: String)
    }
    interface MainPresenter {
        fun search(cep: String)
    }
}