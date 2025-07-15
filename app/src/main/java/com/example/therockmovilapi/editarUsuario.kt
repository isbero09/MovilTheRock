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
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.UsuarioApiService
import com.example.therockmovilapi.Entities.Usuario
import kotlinx.coroutines.launch

class editarUsuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_usuario, container, false)

        lifecycleScope.launch{
            val usuario = UsuarioApiService.getApiManager()
                .getUsuario(arguments?.getString("cedula") ?: "")

            view.findViewById<EditText>(R.id.usuario_cedula_editado)
                .setText(usuario.cedula)

            view.findViewById<EditText>(R.id.usuario_nombre_editado)
                .setText(usuario.nombres)

            view.findViewById<EditText>(R.id.usuario_apellido_editado)
                .setText(usuario.apellidos)

            view.findViewById<EditText>(R.id.usuario_email_editado)
                .setText(usuario.email)

            view.findViewById<EditText>(R.id.usuario_telefono_editado)
                .setText(usuario.telefono)

            view.findViewById<EditText>(R.id.usuario_estado_editado)
                .setText(usuario.estado)

            view.findViewById<EditText>(R.id.usuario_fecha_nacimiento_editado)
                .setText(usuario.fecha_nacimiento)
        }

        view.findViewById<Button>(R.id.btn_update_usuario).setOnClickListener {
            val cedula = view.findViewById<EditText>(R.id.usuario_cedula_editado)
                .text.toString().trim()

            val nombres = view.findViewById<EditText>(R.id.usuario_nombre_editado)
                .text.toString().trim()

            val apellidos = view.findViewById<EditText>(R.id.usuario_apellido_editado)
                .text.toString().trim()

            val email = view.findViewById<EditText>(R.id.usuario_email_editado)
                .text.toString().trim()

            val telefono = view.findViewById<EditText>(R.id.usuario_telefono_editado)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.usuario_estado_editado)
                .text.toString().trim()

            val fecha_nacimiento = view.findViewById<EditText>(R.id.usuario_fecha_nacimiento_editado)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    UsuarioApiService.getApiManager()
                        .putUsuario(
                            Usuario( cedula, nombres, apellidos, email,
                                telefono, estado, fecha_nacimiento
                            ),
                            cedula
                        )
                    Toast.makeText(
                        context, "Usuario actualizado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarUsuario_to_verUsuario, Bundle().apply {
                        putString("cedula", cedula)
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al actualizar usuario: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        return view
    }
}