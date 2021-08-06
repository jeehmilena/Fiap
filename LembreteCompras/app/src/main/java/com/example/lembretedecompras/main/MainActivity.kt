package com.example.lembretedecompras.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityMainBinding
import com.example.lembretedecompras.models.Product
import com.example.lembretedecompras.newproduct.EXTRA_PRODUCT
import com.example.lembretedecompras.newproduct.NewProductActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        setUpList()
        initViewModel()
        initObserver()

    }

    private fun setListeners() {
        binding.fabNovoProduto.setOnClickListener {
            val newProduct = Intent(this, NewProductActivity::class.java)
            newProductActivityResult.launch(newProduct)
        }
    }

    private fun setUpList() {
        mainAdapter = MainAdapter()
        binding.rvProdutos.adapter = mainAdapter
        binding.rvProdutos.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initObserver() {
        mainViewModel.products.observe(this, { list ->
            list.let { mainAdapter.setProducts(list) }
        })
    }

    private val newProductActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getStringExtra(EXTRA_PRODUCT)
                    ?.let { name ->
                        val product = Product(productName = name)
                        mainViewModel.insert(product)
                    }
            } else {
                Toast.makeText(this, "Nenhum produto informado", Toast.LENGTH_LONG).show()
            }
        }

    //Método para criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //Listener para escutar o clique no botão
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btDelete -> {
                confirmaExclusao().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun confirmaExclusao(): AlertDialog {
        return AlertDialog.Builder(this)
            .setTitle("Lembrete de Compras")
            .setMessage("Deseja apagar sua lista?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Apagar") { dialog, _ ->
                mainViewModel.deleteAll()
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}