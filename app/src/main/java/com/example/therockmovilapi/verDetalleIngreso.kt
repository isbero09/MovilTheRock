package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.DetallesIngresosApiService
import kotlinx.coroutines.launch

class verDetalleIngreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_detalle_ingreso, container, false)

        view.findViewById<Button>(R.id.btn_ver_detalleingreso_atras).setOnClickListener{
            findNavController().navigate(R.id.action_verDetalleIngreso_to_listaDetalleIngreso)
        }

        view.findViewById<Button>(R.id.btn_ver_detalleingreso_eliminar).setOnClickListener {
            lifecycleScope.launch {
                DetallesIngresosApiService.getApiManager()
                    .deleteDetallesIngresos(arguments?.getInt("id") ?: 0)

                findNavController()
                    .navigate(R.id.action_verDetalleIngreso_to_listaDetalleIngreso)
            }
        }

        view.findViewById<Button>(R.id.btn_ver_detalleingreso_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verDetalleIngreso_to_editarDetalleingreso, bundle)
        }

        lifecycleScope.launch {
            var detalleingreso = DetallesIngresosApiService.getApiManager().getDetallesIngresos(
                arguments?.getInt("id") ?: 0 )

            view.findViewById<TextView>(R.id.tv_ver_detalle_ingreso_id)
                .text = "ID: " + detalleingreso.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_detalle_ingreso_ingreso)
                .text = "Ingreso: " + detalleingreso.ingreso.toString()

            view.findViewById<TextView>(R.id.tv_ver_detalle_ingreso_producto_id)
                .text = "Producto: " + detalleingreso.producto_id.toString()

            view.findViewById<TextView>(R.id.tv_ver_detalle_ingreso_cantidad)
                .text = "Cantidad: " + detalleingreso.cantidad.toString()
        }
        return view
    }
}