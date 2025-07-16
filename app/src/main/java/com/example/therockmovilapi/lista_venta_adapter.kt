package com.example.therockmovilapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Entities.Venta

class lista_venta_adapter(private var lista: List<Venta>):
    RecyclerView.Adapter<VentaViewHolder>(){
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): VentaViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_venta ,parent,false)

        return VentaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: VentaViewHolder, position: Int) {
        val venta = lista[position]
        holder.cliente.text = venta.cliente
        holder.btn_venta_ver.setOnClickListener {
            val bundle = Bundle()
            .apply {
                putInt("id", venta.id)
            }

            holder
                .itemView
                .findNavController()
                .navigate(R.id.action_listaVenta_to_verVenta, bundle)
        }
    }
}

class VentaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var cliente = view.findViewById<TextView>(R.id.tv_ver_venta_cliente)
    var btn_venta_ver = view.findViewById<Button>(R.id.btn_venta_ver)
}

