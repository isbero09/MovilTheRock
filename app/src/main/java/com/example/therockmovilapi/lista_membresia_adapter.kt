package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.Menbresia

class lista_membresia_adapter(private var lista: List<Menbresia>):
    RecyclerView.Adapter<MembresiaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembresiaViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_membresia ,parent,false)

        return MembresiaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MembresiaViewHolder, position: Int) {
        val membresia = lista[position]
        holder.nombre.text = membresia.nombre
        holder.btn_membresia_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("id", membresia.id)
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_listaMenbresia_to_verMembresia, bundle)
        }

    }
}

class MembresiaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var nombre = view.findViewById<TextView>(R.id.tv_ver_membresia_nombre)
    var btn_membresia_ver = view.findViewById<Button>(R.id.btn_membresia_ver)
}

