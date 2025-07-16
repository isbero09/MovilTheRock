package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.MenbresiasUsuario

class lista_membresiausuario_Adapter(private var lista: List<MenbresiasUsuario>):
    RecyclerView.Adapter<MembresiaUsuarioViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembresiaUsuarioViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_membresiausuario ,parent,false)

        return MembresiaUsuarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MembresiaUsuarioViewHolder, position: Int) {
        val membresiaUsuario = lista[position]
        holder.usuario.text = membresiaUsuario.usuario
        holder.btn_membresiausuario_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("id", membresiaUsuario.id)
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_verMembresiausuario_to_listaMenbresiausuario, bundle)

        }

    }
}

class MembresiaUsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var usuario = view.findViewById<TextView>(R.id.tv_ver_membresiausuario_usuario)
    var btn_membresiausuario_ver = view.findViewById<Button>(R.id.btn_membresiausuario_ver)
}

