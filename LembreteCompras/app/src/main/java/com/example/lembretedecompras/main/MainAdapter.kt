package com.example.lembretedecompras.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lembretedecompras.databinding.ItemRecyclerViewBinding
import com.example.lembretedecompras.models.Product

class MainAdapter(private val deleteListener: (Product) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var list: List<Product> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvProduto.text = list[position].productName
        holder.binding.ivDelete.setOnClickListener {
            deleteListener(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setProducts(products: List<Product>) {
        this.list = products
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}