package com.example.lembretedecompras.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityLoginBinding
import com.example.lembretedecompras.main.MainActivity
import com.example.lembretedecompras.models.RequestState
import com.example.lembretedecompras.models.User

class LoginActivity : AppCompatActivity() {

    private lateinit var animationMascot: Animation
    private lateinit var animationForm: Animation
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnimation()
        hideKeyboard()
        initViewModel()
        initListener()
        initObservables()
        loginViewModel.getUserLogged()
    }

    private fun initAnimation() {
        animationMascot = AnimationUtils.loadAnimation(this, R.anim.frombottom2)
        animationForm = AnimationUtils.loadAnimation(this, R.anim.frombottom)

        binding.containerLogin.startAnimation(animationMascot)
        binding.ivLogin.startAnimation(animationForm)
    }

    private fun hideKeyboard() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    private fun initListener() {
        binding.btLogin.setOnClickListener {
            loginViewModel.login(
                User(
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        }

        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.ivLogin.speed = 2f
                binding.ivLogin.setMinAndMaxProgress(0.0f, 0.65f)
            } else {
                binding.ivLogin.speed = 2f
                binding.ivLogin.setMinAndMaxProgress(0.65f, 1.0f)
            }
            binding.ivLogin.playAnimation()
        }
    }

    private fun initViewModel(){
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun initObservables() {
        loginViewModel.loginState.observe(this, Observer {
            when (it) {
                is RequestState.Success<*> -> {
                    binding.tvPasswordFeedback.text = ""
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is RequestState.Error -> {
                    binding.tvPasswordFeedback.text = it.throwable.message
                }
                is RequestState.Loading -> {
                    Log.i("LEMBRETECOMPRAS", "CARREGANDO")
                }
            }
        })

        loginViewModel.userLoggedState.observe(this, Observer {
            when(it){
                is RequestState.Success<*> -> {
                    binding.etEmail.setText(it.data.toString())
                }
                RequestState.Loading -> TODO()
                is RequestState.Error -> TODO()
            }
        })
    }
}