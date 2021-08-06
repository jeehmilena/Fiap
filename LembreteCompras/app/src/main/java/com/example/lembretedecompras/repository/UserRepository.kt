package com.example.lembretedecompras.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lembretedecompras.models.RequestState
import com.example.lembretedecompras.models.User

private const val SHARED_PREF = "lembretedecompras"
private const val SHARED_PREF_EMAIL = "email"

class UserRepository(private val context: Context) {

    fun login(user: User): LiveData<RequestState<Boolean>> {
        val response = MutableLiveData<RequestState<Boolean>>()

        if (user.email == "fiap@fiap.com.br" && user.password == "12345") {

            val pref = context.getSharedPreferences(SHARED_PREF, 0)
            val edit = pref.edit()
            edit.putString("email", user.email)
            edit.apply()
            response.value = RequestState.Success(true)

        } else {
            response.value = RequestState.Error(Exception("Usuário ou senha invalido"))
        }

        return response
    }

    fun getUserLogged(): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()

        val pref = context.getSharedPreferences(SHARED_PREF, 0)
        val email = pref.getString(SHARED_PREF_EMAIL, "")
        response.value = RequestState.Success(email)

        return response
    }
}