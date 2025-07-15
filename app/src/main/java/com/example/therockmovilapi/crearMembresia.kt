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
import com.example.therockmovilapi.Apis.MenbresiaApiService
import com.example.therockmovilapi.Entities.Menbresia
import kotlinx.coroutines.launch

class crearMembresia : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_membresia, container, false)

        view.findViewById<Button>(R.id.btn_add_membresia).setOnClickListener {
            val nombre = view.findViewById<EditText>(R.id.membresia_nombre_nuevo)
                .text.toString().trim()

            val precio = view.findViewById<EditText>(R.id.membresia_precio_nuevo)
                .text.toString().trim().toDouble()

            val descripcion = view.findViewById<EditText>(R.id.membresia_descripcion_nuevo)
                .text.toString().trim()

            val fechaCreacion = view.findViewById<EditText>(R.id.membresia_fecha_creacion_nuevo)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.membresia_estado_nuevo)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    MenbresiaApiService.getApiManager().postMenbresia(
                        Menbresia(0, nombre, precio, descripcion, fechaCreacion, estado)
                    )
                    Toast.makeText(
                        context, "Membreisa registrada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar membresia: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        return view
    }
}