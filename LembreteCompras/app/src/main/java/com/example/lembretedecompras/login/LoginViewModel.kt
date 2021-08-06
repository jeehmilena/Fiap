package com.example.lembretedecompras.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lembretedecompras.models.RequestState
import com.example.lembretedecompras.models.User
import com.example.lembretedecompras.repository.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val loginState = MutableLiveData<RequestState<Boolean>>()
    val userLoggedState = MutableLiveData<RequestState<String>>()

    fun login(user: User) {
        loginState.value = userRepository.login(user).value
    }

    fun getUserLogged() {
        userLoggedState.value = userRepository.getUserLogged().value
    }
}