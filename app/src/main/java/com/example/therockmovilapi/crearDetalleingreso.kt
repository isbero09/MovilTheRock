package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.therockmovilapi.Apis.DetallesIngresosApiService
import com.example.therockmovilapi.Entities.DetallesIngresos
import kotlinx.coroutines.launch

class crearDetalleingreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_detalleingreso, container, false)

        view.findViewById<Button>(R.id.btn_add_detalleingreso).setOnClickListener {
            val ingreso = view.findViewById<EditText>(R.id.detalleingreso_ingreso_nuevo)
                .text.toString().trim().toInt()

            val productoId = view.findViewById<EditText>(R.id.detalleingreso_productoid_nuevo)
                .text.toString().trim().toInt()

            val cantidad = view.findViewById<EditText>(R.id.detalleingreso_cantidad_nuevo)
                .text.toString().trim().toInt()

            lifecycleScope.launch {
                try {
                    DetallesIngresosApiService.getApiManager().postDetallesIngresos(
                        DetallesIngresos(0, ingreso, productoId, cantidad)
                    )
                    Toast.makeText(
                        context, "Detalle Ingreso registrada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar detallle ingreso : ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }

        return view
    }
}