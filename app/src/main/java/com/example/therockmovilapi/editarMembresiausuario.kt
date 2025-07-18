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
import com.example.therockmovilapi.Apis.MenbresiasUsuarioApiService
import com.example.therockmovilapi.Entities.MenbresiasUsuario
import kotlinx.coroutines.launch

class editarMembresiausuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_membresiausuario, container, false)

        lifecycleScope.launch{
            val membresiaUsuario = MenbresiasUsuarioApiService
                .getApiManager()
                .getMenbresiasUsuario(arguments?.getInt("id") ?: 0)

            view.findViewById< TextView>(R.id.membresiausuario_usuario_editado)
                .text = membresiaUsuario.usuario

            view.findViewById<TextView>(R.id.membresiausuario_membresia_editado)
                .text = membresiaUsuario.membresia

            view.findViewById<TextView>(R.id.membresiausuario_membresiaid_editado)
                .text = membresiaUsuario.membresia_id.toString()

            view.findViewById<TextView>(R.id.membresiausuario_precio_editado)
                .text = membresiaUsuario.precio.toString()

            view.findViewById<TextView>(R.id.membresiausuario_fechapago_editado)
                .text = membresiaUsuario.fecha_pago

            view.findViewById<TextView>(R.id.membresiausuario_fechainicio_editado)
                .text = membresiaUsuario.fecha_inicio

            view.findViewById<TextView>(R.id.membresiausuario_fechafin_editado)
                .text = membresiaUsuario.fecha_fin

            view.findViewById<TextView>(R.id.membresiausuario_estado_editado)
                .text = membresiaUsuario.estado
        }
        view.findViewById<Button>(R.id.btn_update_membresiausuario).setOnClickListener{
            val usuario = view.findViewById<EditText>(R.id.membresiausuario_usuario_editado)
                .text.toString().trim()

            val membresia = view.findViewById<EditText>(R.id.membresiausuario_membresia_editado)
                .text.toString().trim()

            val membresia_id = view.findViewById<EditText>(R.id.membresiausuario_membresiaid_editado)
                .text.toString().trim().toInt()

            val precio = view.findViewById<EditText>(R.id.membresiausuario_precio_editado)
                .text.toString().trim().toDouble()

            val fecha_pago = view.findViewById<EditText>(R.id.membresiausuario_fechapago_editado)
                .text.toString().trim()

            val fecha_inicio = view.findViewById<EditText>(R.id.membresiausuario_fechainicio_editado)
                .text.toString().trim()

            val fecha_fin = view.findViewById<EditText>(R.id.membresiausuario_fechafin_editado)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.membresiausuario_estado_editado)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    MenbresiasUsuarioApiService.getApiManager()
                        .putMenbresiasUsuario(
                            MenbresiasUsuario(
                                arguments?.getInt("id") ?: 0,
                                usuario, membresia, membresia_id, precio, fecha_pago,
                                fecha_inicio, fecha_fin, estado
                            ), arguments?.getInt("id") ?: 0
                        )
                    Toast.makeText(
                        context, "Membresía de usuario actualizada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarMembresiausuario_to_verMembresiausuario, Bundle().apply {
                        putInt("id", arguments?.getInt("id") ?: 0)
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al actualizar membresía de usuario: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }
}