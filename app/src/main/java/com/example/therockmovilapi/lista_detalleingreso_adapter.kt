package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.DetallesIngresos

class lista_detalleingreso_adapter(private var lista: List<DetallesIngresos>):
    RecyclerView.Adapter<DetalleIngresoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleIngresoViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_detalleingreso ,parent,false)

        return DetalleIngresoViewHolder (view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: DetalleIngresoViewHolder, position: Int) {
        val detalleIngreso = lista[position]
        holder.ingreso.text = detalleIngreso.ingreso.toString()
        holder.btn_detalle_ingreso_ver.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("id", detalleIngreso.id)
            }

            holder.itemView.findNavController()
                .navigate(R.id.action_listaDetalleIngreso_to_verDetalleIngreso, bundle)
        }

    }
}

class DetalleIngresoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var ingreso = view.findViewById<TextView>(R.id.tv_ver_detalle_ingreso_ingreso)
    var btn_detalle_ingreso_ver = view.findViewById<Button>(R.id.btn_detalleingreso_ver)
}

