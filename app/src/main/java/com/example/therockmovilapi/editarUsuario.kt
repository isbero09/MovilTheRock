package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.therockmovilapi.Apis.UsuarioApiService
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

        return view
    }
}