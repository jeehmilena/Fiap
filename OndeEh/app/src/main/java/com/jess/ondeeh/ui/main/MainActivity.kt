package com.jess.ondeeh.ui.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jess.ondeeh.R
import com.jess.ondeeh.data.remote.APIService
import com.jess.ondeeh.model.Address
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var etCEP: EditText
    private lateinit var btPesquisar: Button
    private lateinit var tvLogradouro: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvLocalidade: TextView
    private lateinit var tvUF: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpListener()
    }

    private fun setUpView() {
        etCEP = findViewById(R.id.etCEP)
        btPesquisar = findViewById(R.id.btPesquisar)
        tvLogradouro = findViewById(R.id.tvLogradouro)
        tvBairro = findViewById(R.id.tvBairro)
        tvLocalidade = findViewById(R.id.tvLocalidade)
        tvUF = findViewById(R.id.tvUF)
    }

    private fun setUpListener() {
        btPesquisar.setOnClickListener {
            pesquisar()
        }
    }

    private fun pesquisar() {
        APIService.instance
            ?.searchAddress(etCEP.text.toString())
            ?.enqueue(object : Callback<Address> {
                override fun onResponse(
                    call: Call<Address>,
                    response: Response<Address>
                ) {
                    if (response.isSuccessful) {
                        val endereco = response.body()
                        endereco?.let {
                            tvLogradouro.text =
                                endereco.logradouro
                            tvBairro.text = endereco.bairro
                            tvLocalidade.text =
                                endereco.localidade
                            tvUF.text = endereco.uf
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Endereco não encontrado", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<Address>,
                    t: Throwable
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        t.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
