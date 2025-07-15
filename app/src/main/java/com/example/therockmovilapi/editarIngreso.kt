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
import com.example.therockmovilapi.Apis.IngresoApiService
import com.example.therockmovilapi.Entities.Ingreso
import kotlinx.coroutines.launch

class editarIngreso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_ingreso, container, false)

        lifecycleScope.launch{
            val ingreso = IngresoApiService
                .getApiManager()
                .getIngreso(arguments?.getInt("id") ?: 0)

            view.findViewById<TextView>(R.id.ingreso_cedula_editado)
                .text = ingreso.cedula

            view.findViewById<TextView>(R.id.ingreso_fecha_editado)
                .text = ingreso.fecha

            view.findViewById<TextView>(R.id.ingreso_detalles_editado)
                .text = ingreso.detalles
        }

        view.findViewById<Button>(R.id.btn_update_ingreso).setOnClickListener {
            val cedula = view.findViewById<EditText>(R.id.ingreso_cedula_editado)
                .text.toString().trim()

            val fecha = view.findViewById<EditText>(R.id.ingreso_fecha_editado)
                .text.toString().trim()

            val detalles = view.findViewById<EditText>(R.id.ingreso_detalles_editado)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    IngresoApiService.getApiManager()
                        .putIngreso(
                            Ingreso(
                                arguments?.getInt("id") ?: 0,
                                cedula, fecha, detalles
                            ), arguments?.getInt("id") ?: 0
                        )
                    Toast.makeText(
                        context, "Ingreso actualizado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarIngreso_to_verIngreso, Bundle().apply {
                        putInt("id", arguments?.getInt("id") ?: 0)
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al actualizar ingreso: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        return view
    }

}