package com.jess.modularizacao.ui.main

import androidx.lifecycle.MutableLiveData
import com.jess.domain.model.Product
import com.jess.domain.usecase.GetProductsUseCase
import com.jess.modularizacao.viewmodel.BaseViewModel
import com.jess.modularizacao.viewmodel.StateMachineSingle
import com.jess.modularizacao.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class MainViewModel(
    private val useCase: GetProductsUseCase,
    private val uiScheduler: Scheduler
) : BaseViewModel() {
    val state = MutableLiveData<ViewState<List<Product>>>().apply {
        value = ViewState.Loading
    }

    fun getProducts(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    state.postValue(it)
                },
                {
                    state.postValue(ViewState.Failed(it))
                }
            )
    }
}