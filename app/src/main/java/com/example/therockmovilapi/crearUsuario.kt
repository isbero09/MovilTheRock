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
import com.example.therockmovilapi.Apis.UsuarioApiService
import com.example.therockmovilapi.Entities.Usuario
import kotlinx.coroutines.launch

class crearUsuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_usuario, container, false)

        view.findViewById<Button>(R.id.btn_add_usuario).setOnClickListener {
            val cedula = view.findViewById<EditText>(R.id.usuario_cedula_nuevo)
                .text.toString().trim()

            val nombre = view.findViewById<EditText>(R.id.usuario_nombre_nuevo)
                .text.toString().trim()

            val apellido = view.findViewById<EditText>(R.id.usuario_apellido_nuevo)
                .text.toString().trim()

            val email = view.findViewById<EditText>(R.id.usuario_email_nuevo)
                .text.toString().trim()

            val telefono = view.findViewById<EditText>(R.id.usuario_telefono_nuevo)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.usuario_estado_nuevo)
                .text.toString().trim()

            val fechaNacimiento = view.findViewById<EditText>(R.id.usuario_fecha_nacimiento_nuevo)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    UsuarioApiService.getApiManager().postUsuario(
                        Usuario(cedula, nombre, apellido, email, telefono, estado,
                            fechaNacimiento)
                    )
                    Toast.makeText(
                        context, "Usuario registrada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar usuario: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }
}