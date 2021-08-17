package com.jess.ondeeh.ui.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jess.ondeeh.databinding.ActivityMainBinding
import com.jess.ondeeh.model.Address

class MainActivity : AppCompatActivity(), MainContract.MainView {
    private lateinit var etCEP: EditText
    private lateinit var btPesquisar: Button
    private lateinit var tvLogradouro: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvLocalidade: TextView
    private lateinit var tvUF: TextView

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainPresenter = MainPresenter(this)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btPesquisar.setOnClickListener {
            mainPresenter.search(binding.etCEP.text.toString())
        }
    }

    override fun showAddress(address: Address?) {
        binding.tvLogradouro.text = address?.logradouro
        binding.tvBairro.text = address?.bairro
        binding.tvLocalidade.text = address?.localidade
        binding.tvUF.text = address?.uf
    }

    override fun showError(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}