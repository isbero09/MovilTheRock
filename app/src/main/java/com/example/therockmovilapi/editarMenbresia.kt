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
import com.example.therockmovilapi.Apis.MenbresiaApiService
import com.example.therockmovilapi.Entities.Menbresia
import kotlinx.coroutines.launch

class editarMenbresia : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_menbresia, container, false)

        lifecycleScope.launch{
            val membresia = MenbresiaApiService.getApiManager()
                .getMenbresia(arguments?.getInt("id") ?: 0)

            view.findViewById<TextView>(R.id.membresia_nombre_editado)
                .text = membresia.nombre

            view.findViewById<TextView>(R.id.membresia_precio_editado)
                .text = membresia.precio.toString()

            view.findViewById<TextView>(R.id.membresia_descripcion_editado)
                .text = membresia.descripcion

            view.findViewById<TextView>(R.id.membresia_fecha_creacion_editado)
                .text = membresia.fecha_creacion

            view.findViewById<TextView>(R.id.membresia_estado_editado)
                .text = membresia.estado
        }

        view.findViewById<Button>(R.id.btn_update_membresia).setOnClickListener {
            val nombre = view.findViewById<EditText>(R.id.membresia_nombre_editado)
                .text.toString().trim()

            val precio = view.findViewById<EditText>(R.id.membresia_precio_editado)
                .text.toString().trim().toDouble()

            val descripcion = view.findViewById<EditText>(R.id.membresia_descripcion_editado)
                .text.toString().trim()

            val fecha_creacion = view.findViewById<EditText>(R.id.membresia_fecha_creacion_editado)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.membresia_estado_editado)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    MenbresiaApiService.getApiManager()
                        .putMenbresia(
                            Menbresia(
                                arguments?.getInt("id") ?: 0,
                                nombre,
                                precio,
                                descripcion,
                                fecha_creacion,
                                estado
                            ),
                            arguments?.getInt("id") ?: 0
                        )
                    Toast.makeText(
                        context, "Membresía actualizada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarMenbresia_to_verMembresia, Bundle().apply {
                        putInt("id", arguments?.getInt("id") ?: 0)
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al actualizar membresía: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        return view
    }
}