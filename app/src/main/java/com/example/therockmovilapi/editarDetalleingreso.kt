package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.DetallesIngresosApiService
import com.example.therockmovilapi.Entities.DetallesIngresos
import kotlinx.coroutines.launch

class editarDetalleingreso : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_editar_detalleingreso, container, false)

        lifecycleScope.launch {
            var detalle = DetallesIngresosApiService
                .getApiManager()
                .getDetallesIngresos(arguments?.getInt("id") ?: 0)

            view.findViewById<TextView>(R.id.detalleingreso_ingreso_editado)
                .text = detalle.ingreso.toString()

            view.findViewById<TextView>(R.id.detalleingreso_productoid_editado)
                .text = detalle.producto_id.toString()

            view.findViewById<TextView>(R.id.detalleingreso_cantidad_editado)
                .text = detalle.cantidad.toString()
        }

        view.findViewById<Button>(R.id.btn_update_detalleingreso)
            .setOnClickListener {
                val ingreso = view.findViewById<EditText>(R.id.detalleingreso_ingreso_editado)
                    .text.toString().trim()

                val productoId = view.findViewById<EditText>(R.id.detalleingreso_productoid_editado)
                    .text.toString().trim()

                val cantidad = view.findViewById<EditText>(R.id.detalleingreso_cantidad_editado)
                    .text.toString().trim()

                lifecycleScope.launch {
                    try {
                        DetallesIngresosApiService.getApiManager()
                            .putDetallesIngresos(
                                DetallesIngresos(
                                    arguments?.getInt("id") ?: 0,
                                    ingreso.toInt(), productoId.toInt(), cantidad.toInt()
                                ),arguments?.getInt("id") ?: 0
                            )
                        Toast.makeText(
                            context, "Detalle actualizado exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()

                        findNavController().navigate(R.id.action_editarDetalleingreso_to_verDetalleIngreso, Bundle()
                            .apply {
                                    putInt("id", arguments?.getInt("id") ?: 0)
                                })
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Error al actualizar detalle ingreso: ${e.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        return view
    }
}