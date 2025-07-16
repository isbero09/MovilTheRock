package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.Producto

class lista_producto_adapter(private var lista: List<Producto>):
    RecyclerView.Adapter<ProductoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_producto ,parent,false)

        return ProductoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.nombre.text = producto.nombre
        holder.btn_producto_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("id", producto.id)
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_listaProducto_to_verProducto, bundle)
        }

    }
}

class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var nombre = view.findViewById<TextView>(R.id.tv_ver_producto_nombre)
    var btn_producto_ver = view.findViewById<Button>(R.id.btn_producto_ver)
}

