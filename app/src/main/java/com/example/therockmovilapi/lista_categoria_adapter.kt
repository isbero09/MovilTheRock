package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.Categoria

class lista_categoria_adapter(private var lista: List<Categoria>):
    RecyclerView.Adapter<CategoriaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_usuario ,parent,false)

        return CategoriaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = lista[position]
        holder.nombre.text = categoria.nombre
        holder.btn_categoria_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("id", categoria.id)
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_listaCategoria_to_verCategoria, bundle)
        }

    }
}

class CategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var nombre = view.findViewById<TextView>(R.id.tv_ver_categoria_nombre)
    var btn_categoria_ver = view.findViewById<Button>(R.id.btn_categoria_ver)
}
