package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.Ingreso

class lista_ingreso_adapter(private var lista: List<Ingreso>):
    RecyclerView.Adapter<IngresoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngresoViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_ingreso ,parent,false)

        return IngresoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: IngresoViewHolder, position: Int) {
        val ingreso = lista[position]
        holder.cedula.text = ingreso.cedula
        holder.btn_ingreso_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("id", ingreso.id)  // asumiendo que ingreso tiene id
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_listaIngreso_to_verIngreso, bundle)
        }

    }
}

class IngresoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var cedula = view.findViewById<TextView>(R.id.tv_ver_ingreso_cedula)
    var btn_ingreso_ver = view.findViewById<Button>(R.id.btn_ingreso_ver)
}

