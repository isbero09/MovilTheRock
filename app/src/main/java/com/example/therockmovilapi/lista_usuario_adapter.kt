package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.Usuario

class lista_usuario_adapter(private var lista: List<Usuario>):
    RecyclerView.Adapter<UsuarioViewHolder>(){

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_usuario ,parent,false)

        return UsuarioViewHolder (view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = lista[position]
        holder.nombre.text = usuario.nombres
        holder.btn_usuario_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putString("cedula", usuario.cedula)
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_listaUsuario_to_verUsuario, bundle)

        }

    }
}

class UsuarioViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var nombre = view.findViewById<TextView>(R.id.tv_ver_usuario_nombre)
    var btn_usuario_ver = view.findViewById<Button>(R.id.btn_usuario_ver)
}