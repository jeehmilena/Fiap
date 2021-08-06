package com.example.lembretedecompras.newproduct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lembretedecompras.databinding.ActivityNewProductBinding

const val EXTRA_PRODUCT = "EXTRA PRODUTO"

class NewProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSalvar.setOnClickListener {
            val intent = Intent()
            val product = binding.etProduto.text.toString()
            if (product.isEmpty()) {
                setResult(RESULT_CANCELED, intent)
            } else {
                intent.putExtra(EXTRA_PRODUCT, product)
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}