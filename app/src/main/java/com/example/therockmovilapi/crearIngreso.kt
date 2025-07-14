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
import com.example.therockmovilapi.Apis.IngresoApiService
import com.example.therockmovilapi.Entities.Ingreso
import kotlinx.coroutines.launch

class crearIngreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_ingreso, container, false)

        view.findViewById<Button>(R.id.btn_add_ingreso).setOnClickListener {
            val cedula = view.findViewById<EditText>(R.id.ingreso_cedula_nuevo)
                .text.toString().trim()

            val fecha = view.findViewById<EditText>(R.id.ingreso_fecha_nuevo)
                .text.toString().trim()

            val detalle = view.findViewById<EditText>(R.id.ingreso_detalles_nuevo)
                .text.toString().trim()

            lifecycleScope.launch {
                try{
                    IngresoApiService.getApiManager().postIngreso(
                        Ingreso( cedula, fecha, detalle)
                    )
                    Toast.makeText(
                        context, "Ingreso registrado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar ingreso: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }
}