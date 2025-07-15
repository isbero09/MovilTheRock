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
import com.example.therockmovilapi.Apis.MenbresiasUsuarioApiService
import com.example.therockmovilapi.Entities.MenbresiasUsuario
import kotlinx.coroutines.launch

class crearMenbresiausuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_menbresiausuario, container, false)

        view.findViewById<Button>(R.id.btn_add_membresiausuario).setOnClickListener {
            val usuario = view.findViewById<EditText>(R.id.membresiausuario_usuario_nuevo)
                .text.toString().trim()

            val membresia = view.findViewById<EditText>(R.id.membresiausuario_membresia_nuevo)
                .text.toString().trim()

            val membresiaId = view.findViewById<EditText>(R.id.membresiausuario_membresia_id_nuevo)
                .text.toString().trim().toInt()

            val precio = view.findViewById<EditText>(R.id.membresiausuario_precio_nuevo)
                .text.toString().trim().toDouble()

            val fechaPago = view.findViewById<EditText>(R.id.membresiausuario_fecha_pago_nuevo)
                .text.toString().trim()

            val fechaInicio = view.findViewById<EditText>(R.id.membresiausuario_fecha_inicio_nuevo)
                .text.toString().trim()

            val fechaFin = view.findViewById<EditText>(R.id.membresiausuario_fecha_fin_nuevo)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.membresiausuario_estado_nuevo)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    MenbresiasUsuarioApiService.getApiManager().postMenbresiasUsuario(
                        MenbresiasUsuario(0, usuario, membresia, membresiaId, precio,
                            fechaPago, fechaInicio, fechaFin, estado)
                    )
                    Toast.makeText(
                        context, "Membresia Usuario registrada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar membresia usuario: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }

}